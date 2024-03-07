package com.anjaniy.funfactsbackend.models.dto.response;

public class LoginResponse {
    private String jwtToken;
    private String email;
    private String expirationTime;
    private String userRoleName;

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
            "jwtToken='" + jwtToken + '\'' +
            ", email='" + email + '\'' +
            ", expirationTime='" + expirationTime + '\'' +
            ", userRoleName='" + userRoleName + '\'' +
            '}';
    }
}
