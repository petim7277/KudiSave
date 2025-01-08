package com.example.KudiSave.domain.exceptions.kudi_user_exceptions;

import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import org.springframework.http.HttpStatus;

public class KudiUserAlreadyExistException extends KudiSaveExceptions {
    public KudiUserAlreadyExistException() {}
    public KudiUserAlreadyExistException(String message) {}

    public KudiUserAlreadyExistException(String aKudiUserWithThisEmailAlreadyExist, HttpStatus httpStatus) {
    }
}
