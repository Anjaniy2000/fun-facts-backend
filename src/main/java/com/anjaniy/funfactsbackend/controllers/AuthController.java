package com.anjaniy.funfactsbackend.controllers;

import com.anjaniy.funfactsbackend.models.dto.JsonResponse;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.LoginResponse;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import com.anjaniy.funfactsbackend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<JsonResponse> registration(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        return new ResponseEntity<>(
            authService.registration(userRegistrationRequest),
            HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return null;
    }


    @GetMapping("/greet")
    public String greet() {
        return "Hello";
    }


}
