package com.example.KudiSave.domain.services;


import com.example.KudiSave.application.input.NotificationManagementUseCase;
import com.example.KudiSave.infrastructure.inputs.data.requests.SendEmailRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendEmailResponse;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendKudiEmailRequest;
import com.example.KudiSave.infrastructure.inputs.data.responses.SendKudiEmailResponse;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements NotificationManagementUseCase {
    @Override
    public SendEmailResponse sendEmail(SendEmailRequest sendEmailRequest) {
        return null;
    }

    @Override
    public SendKudiEmailResponse sendKudiMail(SendKudiEmailRequest sendKudiEmailRequest) {
        return null;
    }
}
