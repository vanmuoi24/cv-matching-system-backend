package com.example.smartcv_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
}
