package com.trungntm.app.service.keycloak;

import org.keycloak.representations.idm.CredentialRepresentation;

public class KeycloakCredentials {

    public static CredentialRepresentation createPasswordCredentials(String password) {
        return createPasswordCredentials(password, false);
    }

    public static CredentialRepresentation createPasswordCredentials(String password, boolean temp) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(temp);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }
}
