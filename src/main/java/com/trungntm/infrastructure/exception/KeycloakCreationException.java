package com.trungntm.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class KeycloakCreationException extends AppException {

    public KeycloakCreationException() {
    }

    public KeycloakCreationException(String message) {
        super(message);
    }

    public KeycloakCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
