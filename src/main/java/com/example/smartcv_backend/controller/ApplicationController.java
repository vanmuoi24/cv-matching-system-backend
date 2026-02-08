package com.example.smartcv_backend.controller;

import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.ApplicationResponse;
import com.example.smartcv_backend.service.ApplicationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.smartcv_backend.dto.request.ApplicationCreateRequest;
import com.example.smartcv_backend.dto.request.ApplicationUpdateRequest;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationController {

    ApplicationService applicationService;

    @PostMapping()
    ApiResponse<ApplicationResponse> createApplication(@RequestBody ApplicationCreateRequest request) {
        var result = applicationService.createApplication(request);
        return ApiResponse.<ApplicationResponse>builder().result(result).build();
    }

    @GetMapping("/{id}")
    ApiResponse<ApplicationResponse> getApplicationById(@PathVariable Long id) {
        return ApiResponse.<ApplicationResponse>builder().result(applicationService.getApplicationById(id)).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<ApplicationResponse>> listApplications() {
        return ApiResponse.<List<ApplicationResponse>>builder()
                .result(applicationService.getAllApplication())
                .build();
    }

    @PostMapping("/update/{id}")
    public ApiResponse<ApplicationResponse> updateApplication(
            @PathVariable Long id,
            @RequestBody ApplicationUpdateRequest request) {

        return ApiResponse.<ApplicationResponse>builder().result(applicationService.updateApplication(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ApiResponse.<Void>builder().build();
    }

}
