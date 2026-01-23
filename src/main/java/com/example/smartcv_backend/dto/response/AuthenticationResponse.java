package com.example.smartcv_backend.dto.response;


import java.util.Date;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class AuthenticationResponse {
    String token;
    Date expiryTime;
    UserResponse user;
}