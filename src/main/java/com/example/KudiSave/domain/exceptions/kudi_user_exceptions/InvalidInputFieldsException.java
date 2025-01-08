package com.example.KudiSave.domain.exceptions.kudi_user_exceptions;


import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import org.springframework.http.HttpStatus;

public class InvalidInputFieldsException  extends KudiSaveExceptions {
    public InvalidInputFieldsException() {
        super();
    }
    public InvalidInputFieldsException(String message) {
        super(message);
    }

    public InvalidInputFieldsException(String inputFieldIsEmpty, HttpStatus httpStatus) {
        super(inputFieldIsEmpty, httpStatus);
    }
}
