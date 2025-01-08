package com.example.KudiSave.domain.exceptions;

import org.springframework.http.HttpStatus;

public class KudiSaveExceptions extends RuntimeException{
    public KudiSaveExceptions() {
    }

    public KudiSaveExceptions(String message) {

    }


    public KudiSaveExceptions(String kudiUserCreationFailed, HttpStatus httpStatus) {
    }
}
