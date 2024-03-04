package com.anjaniy.funfactsbackend.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRegistrationRequest {
    private String userName;
    private String email;
    private String password;
    private String roleId;

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
            "userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", roleId='" + roleId + '\'' +
            '}';
    }
}
