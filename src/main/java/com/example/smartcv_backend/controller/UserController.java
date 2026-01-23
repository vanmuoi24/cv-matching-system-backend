package com.example.smartcv_backend.controller;

import com.example.smartcv_backend.dto.request.UserCreateRequest;
import com.example.smartcv_backend.dto.request.UserUpdateRequest;
import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.UserResponse;
import com.example.smartcv_backend.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/register")
    ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/list")
    public ApiResponse<List<UserResponse>> listUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }
    // ApiResponse<PageResponse<UserResponse>> listUser(
    //         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
    //         @RequestParam(value = "size", required = false, defaultValue = "10") int size
    //         ){
    //     return ApiResponse.<PageResponse<UserResponse>>builder()
    //             .result(userService.getAllUser(page, size))
    //             .build();
    // }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @PostMapping("/UpdateUser/{id}")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable Long id,
            @ModelAttribute UserUpdateRequest request) {

        return ApiResponse.<UserResponse>builder().result(userService.updateUser(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ApiResponse.<Void>builder().build();
    }

}
