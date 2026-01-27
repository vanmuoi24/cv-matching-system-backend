package com.example.smartcv_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false, length = 50)
    private String name;

    @Column(nullable = false, unique = false)
    private String description;

    @Column(nullable = false)
    private String website;

    @Column(nullable = false, unique = true)
    private String logoUrl;

    @Column(nullable = false)
    private String status;

    @Column
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "owner_id",
            nullable = false
    )
    private User owner;

    @OneToMany(mappedBy = "company")
    private List<Job> jobList;

}
