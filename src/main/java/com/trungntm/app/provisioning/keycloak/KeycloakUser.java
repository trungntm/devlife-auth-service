package com.trungntm.app.provisioning.keycloak;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUser implements Serializable {

    String username;

    String password;

    String email;

    boolean admin;
}
