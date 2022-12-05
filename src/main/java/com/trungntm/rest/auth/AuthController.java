package com.trungntm.rest.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthController {
    String API_PATH = "/api/v1/auth";

    @PostMapping("/signIn")
    TokenResponseDTO signIn(@Valid @RequestBody SignInDTO body);

    @PostMapping("/register")
    UserCreationDTO register(@Valid @RequestBody UserCreationDTO body);

    @GetMapping("/authenticated/me")
    AuthenticatedUserDTO getAuthenticatedUser();
}
