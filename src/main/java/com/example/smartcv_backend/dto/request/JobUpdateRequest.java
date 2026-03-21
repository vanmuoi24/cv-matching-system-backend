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
public class JobUpdateRequest {
    Long companyId;
    String title;
    String description;
    String requirement;
    String skills;
    String location;

    String jobType;
    String category;

    int minSalary;
    int maxSalary;

    String status;
    LocalDateTime expiredAt;
}
