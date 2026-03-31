package com.example.smartcv_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 50)
    private String fullName;

    @Column(nullable = false, name = "password_hash")
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String status;

    @Column
    private LocalDateTime createAt;

    @Column
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Company> companies;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CandidateProfile profile;

    @OneToMany(mappedBy = "createdBy")
    private List<Job> jobList;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applicationList;

}
