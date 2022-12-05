package com.trungntm.app.service.keycloak.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserKeycloakCreationDTO implements Serializable {

    String username;

    String email;

    String phoneNumber;

    String password;

    String firstName;

    String lastName;

    Map<String, List<String>> attributes;
}
