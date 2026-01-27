package com.example.smartcv_backend.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.smartcv_backend.mapper.UserInfoMapper;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class JobInfoResponse {
    int id;
    UserInfoResponse createdBy;
    CompanyInfoResponse company;
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
    LocalDateTime createAt;
    LocalDateTime expiredAt;
}
