package com.example.KudiSave.domain.services;


import com.example.KudiSave.application.input.AccountManagementUseCase;
import com.example.KudiSave.infrastructure.inputs.data.requests.CreateAccountRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.UpdateAccountDetailsRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.ViewAccountDetailsRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.CreateAccountResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.UpdateAccountDetailsResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.ViewAccountDetailsResponse;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AccountService implements AccountManagementUseCase {
    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        return null;
    }
    public static String generateAccountNumber( ) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomNumber = new StringBuilder(10);
        for (int count = 0; count < 10; count++) {
            int digit = secureRandom.nextInt(10);
            randomNumber.append(digit);
        }
        return randomNumber.toString();
    }
    @Override
    public UpdateAccountDetailsResponse updateAccount(UpdateAccountDetailsRequest updateAccountDetailsRequest) {
        return null;
    }

    @Override
    public ViewAccountDetailsResponse viewAccountProfile(ViewAccountDetailsRequest viewAccountDetailsRequest) {
        return null;
    }
}
