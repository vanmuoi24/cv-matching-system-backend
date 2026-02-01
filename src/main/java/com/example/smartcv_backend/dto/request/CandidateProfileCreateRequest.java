package com.example.smartcv_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CandidateProfileCreateRequest {
    Long userId;
    String summary;
    String skills;
    String experienceYear;
    String cvText;
    String cvFileUrl;
    MultipartFile cvFile;
}
