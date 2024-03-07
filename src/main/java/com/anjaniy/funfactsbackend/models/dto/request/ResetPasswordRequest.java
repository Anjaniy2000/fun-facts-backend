package com.anjaniy.funfactsbackend.models.dto.request;

public class ResetPasswordRequest {
    private String newPassword;
    private String confirmNewPassword;

    public ResetPasswordRequest() {

    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
            "newPassword='" + newPassword + '\'' +
            ", confirmNewPassword='" + confirmNewPassword + '\'' +
            '}';
    }
}
