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
public class CandidateProfileUpdateRequest {
//    Long userId;
    String summary;
    String skills;
    String experienceYear;
    String cvText;
    String cvFileUrl;
    LocalDateTime updatedAt;

}
