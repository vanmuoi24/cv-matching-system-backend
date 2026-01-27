package com.example.smartcv_backend.dto.response;

import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ApplicationResponse {
    
    int id;
    UserInfoResponse candidate;
    JobInfoResponse job;
    Float similarityScore;
    String status;
    LocalDateTime appliedAt;
}