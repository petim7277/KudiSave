package com.example.KudiSave.domain.exceptions.kudi_user_exceptions;

import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import org.springframework.http.HttpStatus;

public class KudiUserNotFoundException extends KudiSaveExceptions {
    public KudiUserNotFoundException() {}
    public KudiUserNotFoundException(String message) {}

    public KudiUserNotFoundException(String kudiUserNotFound, HttpStatus httpStatus) {
    }
}
