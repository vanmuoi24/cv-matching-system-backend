package com.example.smartcv_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidate_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfile {
    @Id
    private Long UserId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String summary;

    @Column(nullable = false)
    private String skills;

    @Column
    private String experienceYear;

    @Column
    private String cvText;

    @Column
    private String cvFileUrl;

    @Column
    private LocalDateTime updateAt;

}
