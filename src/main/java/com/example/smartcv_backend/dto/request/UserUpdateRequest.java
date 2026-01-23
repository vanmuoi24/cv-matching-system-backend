package com.example.smartcv_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserUpdateRequest {
    String full_name;
//    String email;
    String password;
    String role;
    String status;
    LocalDateTime updateAt;
}