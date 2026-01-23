package com.example.smartcv_backend.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.example.smartcv_backend.entity.Application;
import com.example.smartcv_backend.entity.Company;
import com.example.smartcv_backend.entity.Job;
import com.example.smartcv_backend.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class JobResponse {
    int id;
    UserResponse createdBy;
    CompanyResponse companyId;
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
    List<ApplicationResponse> applicationList;
}
