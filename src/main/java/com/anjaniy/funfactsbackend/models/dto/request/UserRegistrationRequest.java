package com.anjaniy.funfactsbackend.models.dto.request;

import com.anjaniy.funfactsbackend.models.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRegistrationRequest {
    private String userName;
    private String email;
    private String password;
    private String userRoleName;

    public UserRegistrationRequest() {

    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
            "userName='" + userName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", userRoleName='" + userRoleName + '\'' +
            '}';
    }
}
