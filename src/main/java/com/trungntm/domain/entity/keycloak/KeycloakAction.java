package com.trungntm.domain.entity.keycloak;

public enum KeycloakAction {

    UPDATE_PASSWORD("UPDATE_PASSWORD");

    private final String action;

    KeycloakAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
