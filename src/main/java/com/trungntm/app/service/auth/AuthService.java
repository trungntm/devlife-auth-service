package com.trungntm.app.service.auth;

import com.trungntm.rest.auth.SignInDTO;
import org.keycloak.representations.AccessTokenResponse;

public interface AuthService {
    AccessTokenResponse getToken(SignInDTO dto);
}
