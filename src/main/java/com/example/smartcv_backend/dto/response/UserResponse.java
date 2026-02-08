package com.example.smartcv_backend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;
import java.util.List;

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
    // String password;
    String email;
    String status;
    LocalDateTime createAt;
    LocalDateTime updateAt;
    String role;
    CandidateProfileResponse profile;
    List<JobInfoResponse> jobList;
    // List<ApplicationResponse> applicationList;
    List<CompanyInfoResponse> companies;

}
