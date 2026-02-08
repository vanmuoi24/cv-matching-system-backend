package com.example.smartcv_backend.dto.response;

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
public class CompanyResponse {
    Long id;
    String name;
    String description;
    String website;
    String logoUrl;
    String status;
    LocalDateTime createAt;
    String role;
    UserInfoResponse owner;
    JobResponse job;
}
