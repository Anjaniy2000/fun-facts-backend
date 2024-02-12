package com.anjaniy.funfactsbackend.api;

import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.dto.response.UserRegistrationResponse;
import jakarta.validation.Valid;

public interface AuthAPI {

    public ResponseEntity<UserRegistrationResponse> userRegistration(UserRegistrationRequest userRegistrationRequest);
}
