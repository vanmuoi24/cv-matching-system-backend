package com.example.smartcv_backend.repository;

import com.example.smartcv_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long count();
    Optional<User> findByEmail(String email);
}
