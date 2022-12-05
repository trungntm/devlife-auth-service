package com.trungntm.app.service.keycloak;

import com.trungntm.app.service.keycloak.DTO.UserKeycloakCreationDTO;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;

public interface UserKeycloakService extends KeycloakService {


    Response create(UserKeycloakCreationDTO userDTO);

    void sendVerificationEmail(String userId);

    AccessTokenResponse getToken(String username, String password);

    UserRepresentation getByUsername(String username);

    void remove(String userId);

    void remove(UserRepresentation user);
}
