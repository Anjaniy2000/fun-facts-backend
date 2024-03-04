package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.models.dto.JsonResponse;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.entities.User;
import com.anjaniy.funfactsbackend.models.entities.UserRole;
import com.anjaniy.funfactsbackend.repositories.UserRepository;
import com.anjaniy.funfactsbackend.repositories.UserRoleRepository;
import com.anjaniy.funfactsbackend.utilities.Validators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public JsonResponse registration(UserRegistrationRequest userRegistrationRequest) {
        JsonResponse jsonResponse = Validators.validate(userRegistrationRequest);
        if(jsonResponse != null) {
            return jsonResponse;
        }

        Optional<UserRole> optionalUserRole = userRoleRepository.findById(UUID.fromString(userRegistrationRequest.getRoleId()));
        UserRole userRole = null;
        if(optionalUserRole.isPresent()) {
            userRole = optionalUserRole.get();
        } else {
            return new JsonResponse(
                "",
                false,
                "Role associated with id " + userRegistrationRequest.getRoleId() + " is not present!"
            );
        }

        User user = new User();
        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setUserRole(userRole);
        userRepository.save(user);

        return new JsonResponse(
            "",
            true,
            "User has been successfully registered, please verify your email!"
        );
    }

    public JsonResponse login(LoginRequest loginRequest) {
        JsonResponse jsonResponse = Validators.validate(loginRequest);
        if(jsonResponse != null) {
            return jsonResponse;
        }
        return null;
    }
}
