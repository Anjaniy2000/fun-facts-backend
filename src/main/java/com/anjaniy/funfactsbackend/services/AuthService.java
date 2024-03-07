package com.anjaniy.funfactsbackend.services;

import com.anjaniy.funfactsbackend.api.AuthAPI;
import com.anjaniy.funfactsbackend.api.MailAPI;
import com.anjaniy.funfactsbackend.models.dto.EmailNotification;
import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.ForgotPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.ResetPasswordRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import com.anjaniy.funfactsbackend.models.entities.ResetPasswordToken;
import com.anjaniy.funfactsbackend.models.entities.User;
import com.anjaniy.funfactsbackend.models.entities.VerificationToken;
import com.anjaniy.funfactsbackend.models.enums.EmailNotificationType;
import com.anjaniy.funfactsbackend.models.enums.TokenType;
import com.anjaniy.funfactsbackend.repositories.ResetPasswordTokenRepository;
import com.anjaniy.funfactsbackend.repositories.UserRepository;
import com.anjaniy.funfactsbackend.repositories.UserRoleRepository;
import com.anjaniy.funfactsbackend.repositories.VerificationTokenRepository;
import com.anjaniy.funfactsbackend.utilities.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.RabbitFuture;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements AuthAPI {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, VerificationTokenRepository verificationTokenRepository, ResetPasswordTokenRepository resetPasswordTokenRepository, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
        this.authenticationManager = authenticationManager;
    }

    /*
    Transaction Management is left with exception handling
     */
    public ResponseEntity registration(UserRegistrationRequest userRegistrationRequest) {
        ResponseEntity responseEntity = Validators.validate(userRegistrationRequest);
        if(responseEntity != null) {
            return responseEntity;
        }

        if(userRepository.findByUserName(userRegistrationRequest.getUserName()).isPresent()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "User associated with username " + userRegistrationRequest.getUserName() + " is already present!"
            );
        }

        if(userRepository.findByEmail(userRegistrationRequest.getEmail()).isPresent()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "User associated with email " + userRegistrationRequest.getEmail() + " is already present!"
            );
        }

        User user = new User();
        user.setUserName(userRegistrationRequest.getUserName());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setUserRole(userRoleRepository.findByName(userRegistrationRequest.getUserRoleName().toUpperCase()).get());
        user.setEnabled(false);
        user = userRepository.save(user);

        String token = generateToken(user, TokenType.VERIFICATION);
        String emailNotificationBody = "Thank you for signing up to Fun Facts, please click on the below url to activate your account: " + MailAPI.ACCOUNT_ACTIVATION_URL + token;

        rabbitTemplate.convertAndSend(
            exchange,
            routingKey,
            new EmailNotification(
                "Please activate your account",
                user.getEmail(),
                emailNotificationBody,
                EmailNotificationType.ACCOUNT_ACTIVATION
            )
        );

        return new ResponseEntity(
            "",
            HttpStatus.CREATED.value(),
            "User has been successfully registered, please activate your account!"
        );

    }

    @Override
    public ResponseEntity accountActivation(String token) {
        Optional<VerificationToken> existingVerificationToken = verificationTokenRepository.findByToken(UUID.fromString(token));
        if(existingVerificationToken.isPresent()) {
            if(LocalDateTime.now().until(existingVerificationToken.get().getExpiryDate(), ChronoUnit.MINUTES) <= 0) {
                return new ResponseEntity(
                    "",
                    HttpStatus.BAD_REQUEST.value(),
                    "Account activation link is expired!, Please contact help desk!"
                );
            } else {
                User existingUser = existingVerificationToken.get().getUser();
                existingUser.setEnabled(true);
                userRepository.save(existingUser);
                verificationTokenRepository.delete(existingVerificationToken.get());
                return new ResponseEntity(
                    "",
                    HttpStatus.OK.value(),
                    "Account activated successfully!"
                );
            }
        } else {
            return new ResponseEntity(
                "",
                HttpStatus.NOT_FOUND.value(),
                "Verification token: " + token + " is not found!"
            );
        }
    }

    public ResponseEntity login(LoginRequest loginRequest) {
        ResponseEntity responseEntity = Validators.validate(loginRequest);
        if(responseEntity != null) {
            return responseEntity;
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<User> existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if(existingUser.isPresent()) {
            User user = existingUser.get();
            if(!user.isEnabled()) {
                return new ResponseEntity(
                    "",
                    HttpStatus.NOT_FOUND.value(),
                    "User associated with email " + loginRequest.getEmail() + " is not activated yet, please activate "
                );
            }
        } else {
            return new ResponseEntity(
                "",
                HttpStatus.NOT_FOUND.value(),
                "User associated with email " + loginRequest.getEmail() + " is not found!"
            );
        }
        return null;
    }

    @Override
    public ResponseEntity forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        if(!Validators.validateEmail(forgotPasswordRequest.getEmail())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid email is required!"
            );
        }
        Optional<User> existingUser = userRepository.findByEmail(forgotPasswordRequest.getEmail());
        if(existingUser.isPresent()) {
            String token = generateToken(existingUser.get(), TokenType.RESET_PASSWORD);
            String emailNotificationBody = "Here is the reset password link: " + MailAPI.RESET_PASSWORD_URL + token;
            rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                new EmailNotification(
                    "Reset password request",
                    forgotPasswordRequest.getEmail(),
                    emailNotificationBody,
                    EmailNotificationType.RESET_PASSWORD
                )
            );
            return new ResponseEntity(
                "",
                HttpStatus.OK.value(),
                "Please check your email for a password reset link!"
            );
        } else {
            return new ResponseEntity(
                "",
                HttpStatus.NOT_FOUND.value(),
                "User associated with email " + forgotPasswordRequest.getEmail() + " is not found!"
            );
        }
    }

    @Override
    public ResponseEntity resetPassword(String token, ResetPasswordRequest resetPasswordRequest) {
        Optional<ResetPasswordToken> existingResetPasswordToken = resetPasswordTokenRepository.findByToken(UUID.fromString(token));
        if(existingResetPasswordToken.isPresent()) {
            if(LocalDateTime.now().until(existingResetPasswordToken.get().getExpiryDate(), ChronoUnit.MINUTES) <= 0) {
                return new ResponseEntity(
                    "",
                    HttpStatus.BAD_REQUEST.value(),
                    "Reset password link is expired!, Please generate a new reset password request."
                );
            } else {
                if(resetPasswordRequest.getNewPassword().trim().contentEquals(resetPasswordRequest.getConfirmNewPassword().trim())) {
                    User existingUser = existingResetPasswordToken.get().getUser();
                    existingUser.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
                    userRepository.save(existingUser);
                    resetPasswordTokenRepository.delete(existingResetPasswordToken.get());

                    return new ResponseEntity(
                        "",
                        HttpStatus.OK.value(),
                        "Password has been successfully reset!"
                    );
                } else {
                    return new ResponseEntity(
                        "",
                        HttpStatus.BAD_REQUEST.value(),
                        "New password and confirm new password are not equal!"
                    );
                }
            }
        } else {
            return new ResponseEntity(
                "",
                HttpStatus.NOT_FOUND.value(),
                "Reset password token: " + token + " is not found!"
            );
        }
    }

    private String generateToken(User user, TokenType tokenType) {
        UUID token = UUID.randomUUID();
        if(tokenType.equals(TokenType.VERIFICATION)) {
            verificationTokenRepository.save(new VerificationToken(token, user, LocalDateTime.now().plusDays(1)));
        } else {
            resetPasswordTokenRepository.save(new ResetPasswordToken(token, user, LocalDateTime.now().plusDays(1)));
        }
        return token.toString();
    }

}
