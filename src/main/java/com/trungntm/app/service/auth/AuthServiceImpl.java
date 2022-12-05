package com.trungntm.app.service.auth;

import com.trungntm.app.service.keycloak.UserKeycloakService;
import com.trungntm.rest.auth.SignInDTO;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserKeycloakService userKeycloakService;

    @Override
    public AccessTokenResponse getToken(SignInDTO dto) {
        return userKeycloakService.getToken(dto.username(), dto.password());
    }
}
