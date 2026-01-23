package com.example.smartcv_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "create_by",
            nullable = false
    )
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "company_id",
            nullable = false
    )
    private Company company;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private String requirement;

    @Column(nullable = false)
    private String skills;

    @Column
    private String location;

    @Column(nullable = false)
    private String jobType;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int minSalary;

    @Column(nullable = false)
    private int maxSalary;

    @Column
    private String status;

    @Column
    private LocalDateTime createAt;

    @Column
    private LocalDateTime expiredAt;

    @OneToMany(mappedBy = "job")
    private List<Application> applicationList;

}
