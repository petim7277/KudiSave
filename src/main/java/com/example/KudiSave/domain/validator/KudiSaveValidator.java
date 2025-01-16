package com.example.KudiSave.domain.validator;

import com.example.KudiSave.domain.exceptions.ErrorMessages;
import com.example.KudiSave.domain.exceptions.kudi_user_exceptions.InvalidInputFieldsException;
import com.example.KudiSave.infrastructure.inputs.data.requests.SignUpRequest;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;

public class KudiSaveValidator {
    public  static void  validateFields(SignUpRequest signUpRequest) {
     validateName(signUpRequest.getUsername());
     validateEmail(signUpRequest.getEmail());
     validatePhoneNumber(signUpRequest.getPhoneNumber());
    }



    public static void validateName(String name) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(name.trim())) {
            throw new InvalidInputFieldsException(ErrorMessages.INPUT_FIELD_IS_EMPTY, HttpStatus.BAD_REQUEST);
        }
        String pattern = "^[a-zA-Z][a-zA-Z0-9_]{5,20}$";
        if (!name.matches(pattern)) {throw new InvalidInputFieldsException(ErrorMessages.INVALID_USERNAME_FIELD);}
    }

    public static void validateEmail(String email)  {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(email.trim())) {
            throw new InvalidInputFieldsException(ErrorMessages.INPUT_FIELD_IS_EMPTY, HttpStatus.BAD_REQUEST);
        }
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(pattern)) {throw new InvalidInputFieldsException(ErrorMessages.INVALID_EMAIL_FIELD, HttpStatus.BAD_REQUEST);}
    }

    public static void validatePassword(String password) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(password.trim())) {
            throw new InvalidInputFieldsException(ErrorMessages.INPUT_FIELD_IS_EMPTY);
        }
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        if (!password.matches(pattern)) {throw new InvalidInputFieldsException(ErrorMessages.INVALID_PASSWORD_FIELD, HttpStatus.BAD_REQUEST);}
    }
    public static void validatePhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber) || StringUtils.isEmpty(phoneNumber.trim()))
        {throw new InvalidInputFieldsException(ErrorMessages.INPUT_FIELD_IS_EMPTY, HttpStatus.BAD_REQUEST);}
        String pattern = "^[0-9]{11}$";
        if (!phoneNumber.matches(pattern)){throw  new InvalidInputFieldsException(ErrorMessages.INVALID_PHONE_FIELD, HttpStatus.BAD_REQUEST);}
    }

}
