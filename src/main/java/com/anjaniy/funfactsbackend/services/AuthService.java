package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.api.MailAPI;
import com.anjaniy.funfactsbackend.models.dto.EmailNotification;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.entities.User;
import com.anjaniy.funfactsbackend.models.entities.UserRole;
import com.anjaniy.funfactsbackend.models.entities.VerificationToken;
import com.anjaniy.funfactsbackend.repositories.UserRepository;
import com.anjaniy.funfactsbackend.repositories.UserRoleRepository;
import com.anjaniy.funfactsbackend.repositories.VerificationTokenRepository;
import com.anjaniy.funfactsbackend.utilities.Validators;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements AuthAPI {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, VerificationTokenRepository verificationTokenRepository, MailService mailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
    }

    public ResponseEntity registration(UserRegistrationRequest userRegistrationRequest) {
        ResponseEntity responseEntity = Validators.validate(userRegistrationRequest);
        if(responseEntity != null) {
            return responseEntity;
        }

        if(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "User associated with email " + userRegistrationRequest.getEmail() + " is already present!"
            );
        }

        Optional<UserRole> optionalUserRole = userRoleRepository.findById(UUID.fromString(userRegistrationRequest.getRoleId()));
        UserRole userRole = null;
        if(optionalUserRole.isPresent()) {
            userRole = optionalUserRole.get();
        } else {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Role associated with id " + userRegistrationRequest.getRoleId() + " is not present!"
            );
        }

        User user = new User();
        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setUserRole(userRole);
        user.setEnabled(false);
        user = userRepository.save(user);

        String token = generateVerificationToken(user);
        String emailNotificationBody = "Thank you for signing up to Fun Facts, please click on the below url to activate your account: " + MailAPI.ACCOUNT_ACTIVATION_URL + token;
        mailService.send(new EmailNotification("Please activate your account", user.getEmail(), emailNotificationBody));

        return new ResponseEntity(
            "",
            HttpStatus.CREATED.value(),
            "User has been successfully registered, please activate your account!"
        );
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        ResponseEntity responseEntity = Validators.validate(loginRequest);
        if(responseEntity != null) {
            return responseEntity;
        }
        return null;
    }

    private String generateVerificationToken(User user) {
        UUID token = UUID.randomUUID();
        VerificationToken verificationToken = new VerificationToken(
            token,
            user
        );
        verificationTokenRepository.save(verificationToken);
        return token.toString();
    }
}
