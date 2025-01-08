package com.example.KudiSave.infrastructure.output.exception;

import org.springframework.http.HttpStatus;

public class IdentityManagerException extends RuntimeException{
    public IdentityManagerException() {
    }

    public IdentityManagerException(String message) {

    }


    public IdentityManagerException(String kudiUserCreationFailed, HttpStatus httpStatus) {
    }
}
