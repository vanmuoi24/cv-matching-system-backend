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
public class CompanyCreateRequest {

    String name;
    String description;
    String website;
    String logoUrl;
    String status;
    String role;
    Long ownerId;     // chỉ gửi userId
    LocalDateTime createAt;
}
