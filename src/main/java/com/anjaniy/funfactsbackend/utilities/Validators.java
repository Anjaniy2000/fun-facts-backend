package com.anjaniy.funfactsbackend.utilities;

import com.anjaniy.funfactsbackend.models.dto.JsonResponse;
import com.anjaniy.funfactsbackend.models.dto.request.LoginRequest;
import com.anjaniy.funfactsbackend.models.dto.request.UserRegistrationRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static JsonResponse validate(UserRegistrationRequest userRegistrationRequest) {
        if(userRegistrationRequest.getUserName().isBlank() || userRegistrationRequest.getUserName().length() < 10) {
            return new JsonResponse(
                "",
                false,
                "Username is required!"
            );
        }
        if(userRegistrationRequest.getEmail().isBlank()) {
            return new JsonResponse(
                "",
                false,
                "Email is required!"
            );
        }
        if(!validateEmail(userRegistrationRequest.getEmail())) {
            return new JsonResponse(
                "",
                false,
                "Valid email is required!"
            );
        }
        if(userRegistrationRequest.getPassword().isBlank()) {
            return new JsonResponse(
                "",
                false,
                "Password is required!"
            );
        }
        if(!validatePassword(userRegistrationRequest.getPassword())) {
            return new JsonResponse(
                "",
                false,
                "Valid password is required!"
            );
        }
        if(userRegistrationRequest.getRoleId().isBlank()) {
            return new JsonResponse(
                "",
                false,
                "Role is required!"
            );
        }
        return null;
    }

    public static JsonResponse validate(LoginRequest loginRequest) {
        if(loginRequest.getEmail().isBlank()) {
            return new JsonResponse(
                "",
                false,
                "Email is required!"
            );
        }
        if(!validateEmail(loginRequest.getEmail())) {
            return new JsonResponse(
                "",
                false,
                "Valid email is required!"
            );
        }
        if(loginRequest.getPassword().isBlank()) {
            return new JsonResponse(
                "",
                false,
                "Password is required!"
            );
        }
        if(!validatePassword(loginRequest.getPassword())) {
            return new JsonResponse(
                "",
                false,
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
}
