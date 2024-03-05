package com.anjaniy.funfactsbackend.api;

import com.anjaniy.funfactsbackend.models.dto.EmailNotification;

public interface MailAPI {

    void send(EmailNotification emailNotification);
    String ACCOUNT_ACTIVATION_URL = "http://localhost:8080/api/v1/auth/accountActivation/";
}
