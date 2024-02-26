package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.LoginResponse;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthAPI {

    @Override
    public ResponseEntity<UserRegistrationResponse> registration(UserRegistrationRequest userRegistrationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return null;
    }
}
