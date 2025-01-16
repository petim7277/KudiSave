package com.example.KudiSave.application.input;

import com.example.KudiSave.domain.exceptions.KudiSaveExceptions;
import com.example.KudiSave.domain.models.KudiUser;
import com.example.KudiSave.infrastructure.inputs.data.requests.*;
import com.example.KudiSave.infrastructure.inputs.data.responses.*;

public interface KudiUserManagementUseCase {

    KudiUser signUp(KudiUser domainObject) throws KudiSaveExceptions;

    String createPassword(KudiUser kudiUser) throws KudiSaveExceptions;

    String signIn(KudiUser userDomainObject)  throws KudiSaveExceptions;

    RecieveMoneyResponse recieveMoney(RecieveMoneyRequest recieveMoneyRequest);


    ViewUserDetailsResponse viewUserProfile(ViewUserDetailsRequest viewUserDetailsRequest);




}
