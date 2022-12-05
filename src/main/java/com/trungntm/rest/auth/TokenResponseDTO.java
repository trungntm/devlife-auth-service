package com.trungntm.rest.auth;

import com.github.dozermapper.core.Mapping;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TokenResponseDTO implements Serializable {

    @Mapping(value = "token")
    private String token;

    @Mapping(value = "expiresIn")
    private Integer expiresIn;

    @Mapping(value = "tokenType")
    private String tokenType;

    @Mapping(value = "scope")
    private String scope;
}
