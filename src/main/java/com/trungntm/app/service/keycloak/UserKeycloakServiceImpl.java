package com.trungntm.app.service.keycloak;

import com.trungntm.app.service.keycloak.DTO.UserKeycloakCreationDTO;
import com.trungntm.security.infrastructure.provider.KeycloakProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
public class UserKeycloakServiceImpl extends KeycloakServiceImpl implements UserKeycloakService {

    @Autowired
    private KeycloakProvider provider;

    @Override
    public Response create(UserKeycloakCreationDTO userDTO) {
        CredentialRepresentation credential = KeycloakCredentials.createPasswordCredentials(userDTO.getPassword());

        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setCredentials(Collections.singletonList(credential));
        userDTO.getAttributes().put("phone-number", Collections.singletonList(userDTO.getPhoneNumber()));
        user.setAttributes(userDTO.getAttributes());
        user.setEnabled(true);

        return getRealmResource().users().create(user);
    }

    @Override
    public void sendVerificationEmail(String userId) {
        getRealmResource().users().get(userId).sendVerifyEmail();
    }

    @Override
    public AccessTokenResponse getToken(String username, String password) {
        Keycloak keycloak = provider.getInstance(username, password);
        return keycloak
                .tokenManager()
                .getAccessToken();
    }

    @Override
    public UserRepresentation getByUsername(String username) {
        return getRealmResource().users().search(username).get(0);
    }

    @Override
    public void remove(String userId) {
        getRealmResource().users().get(userId).remove();
    }

    @Override
    public void remove(UserRepresentation user) {
        remove(user.getId());
    }
}
