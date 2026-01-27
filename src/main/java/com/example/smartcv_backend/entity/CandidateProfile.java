package com.example.smartcv_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "candidate_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfile {
    @Id
    private Long userId;

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
