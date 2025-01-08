package com.example.KudiSave.application.input;

import com.example.KudiSave.infrastructure.inputs.data.requests.CreateAccountRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.UpdateAccountDetailsRequest;
import com.example.KudiSave.infrastructure.inputs.data.requests.ViewAccountDetailsRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.CreateAccountResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.UpdateAccountDetailsResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.ViewAccountDetailsResponse;

public interface AccountManagementUseCase {
    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);
    ViewAccountDetailsResponse viewAccountProfile(ViewAccountDetailsRequest viewAccountDetailsRequest);
    UpdateAccountDetailsResponse updateAccount(UpdateAccountDetailsRequest updateAccountDetailsRequest);

}
