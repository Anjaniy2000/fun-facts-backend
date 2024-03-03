package com.anjaniy.funfactsbackend.controllers;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.LoginResponse;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import com.anjaniy.funfactsbackend.services.AuthService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthAPI {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    @Override
    public ResponseEntity<UserRegistrationResponse> registration(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        return authService.registration(userRegistrationRequest);
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return null;
    }


    @GetMapping("/greet")
    public String greet() {
        return "Hello";
    }


}
