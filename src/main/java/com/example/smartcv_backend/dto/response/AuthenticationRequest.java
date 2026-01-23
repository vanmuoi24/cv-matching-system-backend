package com.example.smartcv_backend.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AuthenticationRequest {

    // Đặt message là tên của Enum
    @NotBlank(message = "EMAIL_IS_REQUIRED")
    @Email(message = "INVALID_EMAIL_FORMAT") // (Bạn cần tạo Enum này nếu chưa có)
    private String email;

    @NotBlank(message = "PASSWORD_IS_REQUIRED") // (Ví dụ cho password)
    private String password;

}