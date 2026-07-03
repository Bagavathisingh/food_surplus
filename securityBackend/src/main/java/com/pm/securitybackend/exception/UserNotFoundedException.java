package com.pm.securitybackend.exception;

public class UserNotFoundedException extends RuntimeException {
    public UserNotFoundedException(String message) {
        super(message);
    }
}
