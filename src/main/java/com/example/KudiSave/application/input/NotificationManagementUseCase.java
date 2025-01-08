package com.example.KudiSave.application.input;

import com.example.KudiSave.infrastructure.inputs.data.requests.SendEmailRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendEmailResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendKudiEmailRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendKudiEmailResponse;

public interface NotificationManagementUseCase {
    SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest);
    SendKudiEmailResponse sendKudiMail(SendKudiEmailRequest sendKudiEmailRequest);

}
