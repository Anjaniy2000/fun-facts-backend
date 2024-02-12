package com.anjaniy.funfactsbackend.controllers;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import com.anjaniy.funfactsbackend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthAPI {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @PostMapping("/userRegistration")
    public ResponseEntity<UserRegistrationResponse> userRegistration(@RequestBody @Valid UserRegistrationRequest userRegistrationRequest) {
        System.out.println(userRegistrationRequest.getUserName());
        System.out.println(userRegistrationRequest.getEmail());
        System.out.println(userRegistrationRequest.getPassword());
        return authService.userRegistration(userRegistrationRequest);
    }
}
