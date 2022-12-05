package com.trungntm.rest.auth;

import com.trungntm.app.service.auth.AuthService;
import com.trungntm.app.service.user.UserService;
import com.trungntm.domain.entity.user.User;
import com.trungntm.infrastructure.exception.DataNotFoundException;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.ws.rs.NotAuthorizedException;

@RestController
@RequestMapping(AuthController.API_PATH)
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthRestConverter converter;

    @Autowired
    private AuthService service;

    @Autowired
    private UserService userService;

    @Override
    public TokenResponseDTO signIn(@Valid @RequestBody SignInDTO body) {
        return converter.toTokenResponseDTO(service.getToken(body));
    }

    @Override
    public UserCreationDTO register(@Valid @RequestBody UserCreationDTO body) {
        User userCreated = userService.create(body);
        return userService.getConverter().toUserCreationDTO(userCreated);
    }

    @Override
    public AuthenticatedUserDTO getAuthenticatedUser() {
        Authentication authentication = (KeycloakAuthenticationToken)SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();

        User userAuthenticated = userService.getByUsername(authentication.getName())
                .orElseThrow(() -> new DataNotFoundException("User does not exist or has been disabled"));

        return userService.getConverter().toAuthenticatedUser(userAuthenticated);
    }
}
