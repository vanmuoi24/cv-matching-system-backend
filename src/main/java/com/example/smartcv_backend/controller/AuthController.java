package com.example.smartcv_backend.controller;

import com.example.smartcv_backend.dto.request.ChangePasswordRequest;
import com.example.smartcv_backend.dto.request.LogoutRequest;
import com.example.smartcv_backend.dto.request.RefreshRequest;
import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.AuthenticationRequest;
import com.example.smartcv_backend.dto.response.AuthenticationResponse;
import com.example.smartcv_backend.service.AuthService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login (@RequestBody @Valid AuthenticationRequest request){
        var result = authService.authenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result).build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().result(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/Change-password")
    ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest request) throws ParseException, JOSEException {
        authService.changePassword(request);
        return ApiResponse.<Void>builder().message("Change password successfully").build();
    }






}