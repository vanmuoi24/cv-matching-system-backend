package com.example.smartcv_backend.dto.response;

import com.example.smartcv_backend.entity.Application;
import com.example.smartcv_backend.entity.CandidateProfile;
import com.example.smartcv_backend.entity.Company;
import com.example.smartcv_backend.entity.Job;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
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
public class UserResponse {

    Long id;
    String fullName;
//    String password;
    String email;
    String status;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String role;
    CandidateProfileResponse profile;
    List<JobResponse> jobList;
    List<ApplicationResponse> applicationList;
    List<CompanyResponse> companies;

}
