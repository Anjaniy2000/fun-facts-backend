package com.anjaniy.funfactsbackend.api;

import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.LoginResponse;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;

public interface AuthAPI {

    public ResponseEntity<UserRegistrationResponse> registration(UserRegistrationRequest userRegistrationRequest);
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}
