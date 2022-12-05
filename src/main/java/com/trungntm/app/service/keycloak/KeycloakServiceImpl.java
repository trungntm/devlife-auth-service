package com.trungntm.app.service.keycloak;

import com.trungntm.security.infrastructure.provider.KeycloakProvider;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Autowired
    protected KeycloakProvider instance;

    @Value("${keycloak.realm}")
    protected String realm;

    private Keycloak getKeycloak() {
        return instance.getInstance();
    }

    protected RealmResource getRealmResource() {
        return getKeycloak().realm(realm);
    }
}
