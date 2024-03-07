package com.anjaniy.funfactsbackend.controllers;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.ForgotPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.ResetPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.services.AuthService;
import jakarta.validation.Valid;
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
    public ResponseEntity registration(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return authService.registration(userRegistrationRequest);
    }

    @Override
    @GetMapping("/account-activation/{token}")
    public ResponseEntity accountActivation(@PathVariable("token") String token) {
        return authService.accountActivation(token);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity login(LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @Override
    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return authService.forgotPassword(forgotPasswordRequest);
    }

    @Override
    @PostMapping("/reset-password/{token}")
    public ResponseEntity resetPassword(@PathVariable("token") String token, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        return authService.resetPassword(token, resetPasswordRequest);
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello";
    }


}
