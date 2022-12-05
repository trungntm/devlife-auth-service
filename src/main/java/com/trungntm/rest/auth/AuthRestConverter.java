package com.trungntm.rest.auth;

import com.trungntm.Converter;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthRestConverter {

    private static final Converter.Instance<TokenResponseDTO, AccessTokenResponse> tokenConverter = Converter.getConverter(TokenResponseDTO.class, AccessTokenResponse.class);

    public TokenResponseDTO toTokenResponseDTO(AccessTokenResponse token) {
        return tokenConverter.backwards(token);
    }
}
