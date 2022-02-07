package com.nvc.backendtest.model.exceptions;

import static java.lang.String.format;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message, String email) {
        super(format(message, email));
    }
}
