package com.anjaniy.funfactsbackend.controllers;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthAPI {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Override
    @PostMapping("/registration")
    public ResponseEntity registration(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        return authService.registration(userRegistrationRequest);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest) {
        return null;
    }


    @GetMapping("/greet")
    public String greet() {
        return "Hello";
    }


}
