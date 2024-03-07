package com.anjaniy.funfactsbackend.utilities;

import com.anjaniy.funfactsbackend.models.dto.ResponseEntity;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static ResponseEntity validate(UserRegistrationRequest userRegistrationRequest) {
        if(userRegistrationRequest.getUserName().isBlank() || userRegistrationRequest.getUserName().length() < 10) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Username is required!"
            );
        }
        if(userRegistrationRequest.getEmail().isBlank()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Email is required!"
            );
        }
        if(!validateEmail(userRegistrationRequest.getEmail())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid email is required!"
            );
        }
        if(userRegistrationRequest.getPassword().isBlank()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Password is required!"
            );
        }
        if(!validatePassword(userRegistrationRequest.getPassword())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid password is required!"
            );
        }
        if(userRegistrationRequest.getUserRoleName().isBlank()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "User role is required!"
            );
        }
        if(!validateUserRole(userRegistrationRequest.getUserRoleName().toUpperCase())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid user role is required!"
            );
        }
        return null;
    }

    public static ResponseEntity validate(LoginRequest loginRequest) {
        if(loginRequest.getEmail().isBlank()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Email is required!"
            );
        }
        if(!validateEmail(loginRequest.getEmail())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid email is required!"
            );
        }
        if(loginRequest.getPassword().isBlank()) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Password is required!"
            );
        }
        if(!validatePassword(loginRequest.getPassword())) {
            return new ResponseEntity(
                "",
                HttpStatus.BAD_REQUEST.value(),
                "Valid password is required!"
            );
        }
        return null;
    }

    public static boolean validateEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean validateUserRole(String userRoleName) {
        return List.of("ADMIN", "USER", "MODERATOR").contains(userRoleName);
    }
}
