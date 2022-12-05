package com.trungntm.domain.entity.keycloak;

public enum KeycloakGroup {

    super_admin("super_admin"),
    standard_admin("standard_admin"),
    support_admin("support_admin");

    private String group;

    KeycloakGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }
}
