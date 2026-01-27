package com.example.smartcv_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    OLD_PASSWORD_NOT_MATCH(1005, "Old password not match", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed or wrong password ", HttpStatus.NOT_FOUND),
    User_name(10099, "User not existed", HttpStatus.NOT_FOUND),

    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_EMAIL(1008, "Invalid email address", HttpStatus.BAD_REQUEST),
    EMAIL_IS_REQUIRED(1009, "Email is required", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1010, "Invalid token", HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN(1011, "Token is expired", HttpStatus.UNAUTHORIZED),
    PASSWORD_NOT_MATCH(1012, "Password not match", HttpStatus.BAD_REQUEST),

    INVALID_JOB(1012, "Job not existed", HttpStatus.NOT_FOUND),
    APPLICATION_EXISTED(1013, "Application existed", HttpStatus.BAD_REQUEST),
    APPLICATION_NOT_EXISTED(1014, "Application not existed", HttpStatus.NOT_FOUND),

    JOB_NOT_EXISTED(1015, "Job not existed", HttpStatus.NOT_FOUND),

    CANDIDATE_PROFILE_NOT_EXISTED(1016, "Candidate profile not existed", HttpStatus.NOT_FOUND)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
