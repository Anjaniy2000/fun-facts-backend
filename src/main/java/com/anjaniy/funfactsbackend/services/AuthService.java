package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.LoginResponse;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import com.anjaniy.funfactsbackend.models.entities.User;
import com.anjaniy.funfactsbackend.utilities.Validators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthAPI {

    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<UserRegistrationResponse> registration(UserRegistrationRequest userRegistrationRequest) {
        if(Validators.validate(userRegistrationRequest) != null) {
            Validators.validate(userRegistrationRequest);
        }
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return null;
    }
}
