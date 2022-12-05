package com.trungntm.app.provisioning.keycloak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trungntm.domain.entity.keycloak.KeycloakGroup;
import com.trungntm.security.infrastructure.provider.KeycloakProvider;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KeycloakInitializer implements InitializingBean {

    @Autowired
    private KeycloakProvider instance;

    private Keycloak keycloak;

    @Autowired
    private KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties;

    @Autowired
    private ObjectMapper mapper;

    private static String REALM_ID;

    private static final String INIT_KEYCLOAK_PATH = "keycloak/keycloak-realm.json";
    private static final String INIT_KEYCLOAK_USERS_PATH =
            "keycloak/keycloak-users.json";

    @PostConstruct
    public void initializer() {
        this.keycloak = instance.getInstance(
                keycloakInitializerConfigurationProperties.getUrl(),
                keycloakInitializerConfigurationProperties.getMasterRealm(),
                keycloakInitializerConfigurationProperties.getClientId(),
                keycloakInitializerConfigurationProperties.getUsername(),
                keycloakInitializerConfigurationProperties.getPassword()
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        REALM_ID = keycloakInitializerConfigurationProperties.getApplicationRealm();

        if (keycloakInitializerConfigurationProperties.initializeOnStartup()) {
            init(false);
        }
    }

    public void init(boolean overwrite) {
        log.info("Keycloak Initializer start ...");
        List<RealmRepresentation> realms = keycloak.realms().findAll();
        boolean isAlreadyInitialized =
                realms.stream().anyMatch(realm -> realm.getId().equals(REALM_ID));

        if (isAlreadyInitialized && overwrite) {
            reset();
        }

        if (!isAlreadyInitialized || overwrite) {
            initKeycloak();

            log.info("Keycloak initialized successfully");
        } else {
            log.warn("Keycloak initialization cancelled: realm already exist");
        }
    }

    private void initKeycloak() {
        initKeycloakRealm();
        initKeycloakUsers();
    }

    private void initKeycloakRealm() {

        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(REALM_ID);
        realmRepresentation.setId(REALM_ID);

        Resource resource = new ClassPathResource(INIT_KEYCLOAK_PATH);
        try {
            RealmRepresentation realmRepresentationToImport =
                    mapper.readValue(resource.getFile(), RealmRepresentation.class);
            keycloak.realms().create(realmRepresentationToImport);
        } catch (IOException e) {
            String errorMessage =
                    String.format("Failed to import keycloak realm representation : %s", e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    private void initKeycloakUsers() {

        List<KeycloakUser> users;
        try {
            Resource resource = new ClassPathResource(INIT_KEYCLOAK_USERS_PATH);
            users =
                    mapper.readValue(
                            resource.getFile(),
                            mapper.getTypeFactory().constructCollectionType(ArrayList.class, KeycloakUser.class));
        } catch (IOException e) {
            String errorMessage = String.format("Failed to read keycloak users : %s", e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }

        users.forEach(this::initKeycloakUser);
    }

    private void initKeycloakUser(KeycloakUser user) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        CredentialRepresentation userCredentialRepresentation = new CredentialRepresentation();
        userCredentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        userCredentialRepresentation.setTemporary(false);
        userCredentialRepresentation.setValue(user.getPassword());
        userRepresentation.setCredentials(List.of(userCredentialRepresentation));
        keycloak.realm(REALM_ID).users().create(userRepresentation);

        if (user.isAdmin()) {
            userRepresentation =
                    keycloak.realm(REALM_ID).users().search(user.getUsername()).get(0);
            UserResource userResource =
                    keycloak.realm(REALM_ID).users().get(userRepresentation.getId());

            GroupResource groupResource
                    = keycloak.realm(REALM_ID).groups().group(KeycloakGroup.super_admin.getGroup());
            GroupRepresentation group = groupResource.toRepresentation();
            userResource.joinGroup(group.getId());
        }
    }

    public void reset() {
        try {
            keycloak.realm(REALM_ID).remove();
        } catch (Exception e) {
            log.error("Failed to reset Keycloak", e);
        }
    }
}

