package com.anjaniy.funfactsbackend.models.dto.request;

public class ForgotPasswordRequest {
    private String email;

    public ForgotPasswordRequest() {

    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ForgotPasswordRequest{" +
            "email='" + email + '\'' +
            '}';
    }
}
