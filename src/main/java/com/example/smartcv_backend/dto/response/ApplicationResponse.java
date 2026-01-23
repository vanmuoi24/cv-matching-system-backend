package com.example.smartcv_backend.dto.response;

import java.time.LocalDateTime;
import com.example.smartcv_backend.entity.Job;
import com.example.smartcv_backend.entity.User;
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
    User candidate;
    Job job;
    float similarityScore;
    String status;
    LocalDateTime appliedAt;
}