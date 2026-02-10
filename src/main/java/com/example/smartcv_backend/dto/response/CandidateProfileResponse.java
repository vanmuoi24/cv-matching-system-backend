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
public class CandidateProfileResponse {
    // private int userId;
    private UserInfoResponse user;
    private String summary;
    private String skills;
    private String experienceYear;
    private String cvText;
    private String cvFileUrl;
    private LocalDateTime updateAt;
}
