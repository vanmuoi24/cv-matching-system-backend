package com.example.smartcv_backend.service;

import com.example.smartcv_backend.dto.request.UserCreateRequest;
import com.example.smartcv_backend.dto.request.UserUpdateRequest;
import com.example.smartcv_backend.dto.response.UserResponse;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.UserMapper;
import com.example.smartcv_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreateRequest request) {

        // Validate các trường bắt buộc
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        // Check email tồn tại
        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new AppException(ErrorCode.USER_EXISTED);
                });

        // Map DTO -> Entity
        User user = userMapper.toUser(request);

        // Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Use values from request or defaults
        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        } else {
            user.setFullName("");
        }

        user.setStatus("ACTIVE"); // Set default status to ACTIVE instead of empty

        if (request.getRole() != null && !request.getRole().isEmpty()) {
            user.setRole(request.getRole());
        } else {
            user.setRole("USER");
        }

        user.setCreateAt(LocalDateTime.now());

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new AppException(ErrorCode.USER_EXISTED); // email/username trùng
        }

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.User_name));

    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest req) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Update phone và address nếu khác null
        Optional.ofNullable(req.getFullName()).ifPresent(user::setFullName);
        Optional.ofNullable(req.getRole()).ifPresent(user::setRole);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.delete(user);
    }
}
