package com.anjaniy.funfactsbackend.api;

import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.ForgotPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.ResetPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;

public interface AuthAPI {
    ResponseEntity registration(UserRegistrationRequest registrationRequest);
    ResponseEntity accountActivation(String token);
    ResponseEntity login(LoginRequest loginRequest);
    ResponseEntity forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    ResponseEntity resetPassword(String token, ResetPasswordRequest resetPasswordRequest);
}
