package com.example.smartcv_backend.repository;

import com.example.smartcv_backend.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
