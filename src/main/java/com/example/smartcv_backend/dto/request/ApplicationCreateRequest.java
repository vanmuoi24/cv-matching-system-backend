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
public class ApplicationCreateRequest {

    Long candidateId;
    Long jobId;
    float similarityScore;
    String status;
    LocalDateTime appliedAt;
}
