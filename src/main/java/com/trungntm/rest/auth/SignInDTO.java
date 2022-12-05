package com.trungntm.rest.auth;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public record SignInDTO(@NotNull @Length(min = 2, max = 50)
                        String username,
                        @NotNull @Length(min = 6)
                        String password,
                        boolean rememberMe) {
}
