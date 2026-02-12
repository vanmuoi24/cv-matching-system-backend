package com.example.smartcv_backend.config;

import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAdmin() {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setFullName("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                admin.setStatus("ACTIVE");
                admin.setCreateAt(LocalDateTime.now());

                userRepository.save(admin);

                System.out.println("✅ Admin account created!");
            }
            // If password hasn't been encoded use this code for set password dafault = 123456
            // else {
            //     List<User> users = userRepository.findAll();
            //     for (User user : users) {
            //             user.setPassword(passwordEncoder.encode("123456"));
            //             userRepository.save(user);
            //             System.out.println("✅ Password for user " + user.getEmail() + " has been encoded.");
            //     }
            // }
        };
    }
}
