package com.anjaniy.funfactsbackend.api;

import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;

public interface AuthAPI {
    ResponseEntity registration(UserRegistrationRequest registrationRequest);
    ResponseEntity login(LoginRequest loginRequest);
}
