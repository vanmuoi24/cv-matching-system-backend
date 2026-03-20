package com.example.smartcv_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CompanyUpdateRequest {
    String name;
    String description;
    String website;
    String logoUrl;
    MultipartFile logo;
    String status;
    Long ownerId; // chỉ gửi userId
    LocalDateTime createAt;

}
