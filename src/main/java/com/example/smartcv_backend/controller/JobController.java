package com.example.smartcv_backend.controller;

import com.example.smartcv_backend.dto.request.JobCreateRequest;
import com.example.smartcv_backend.dto.request.JobUpdateRequest;
import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.JobInfoResponse;
import com.example.smartcv_backend.dto.response.JobResponse;
import com.example.smartcv_backend.service.JobService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobController {

    JobService jobService;

    @PostMapping
    ApiResponse<JobResponse> createJob(@RequestBody JobCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();  
        Long userId =  Long.valueOf(jwt.getSubject());
        request.setCreatedById(userId);
        return ApiResponse.<JobResponse>builder().result(jobService.createJob(request)).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<JobResponse>> listJobs() {
        return ApiResponse.<List<JobResponse>>builder().result(jobService.getAllJob()).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<JobInfoResponse> getJobById(@PathVariable Long id) {
        return ApiResponse.<JobInfoResponse>builder().result(jobService.getJobById(id)).build();
    }

    @PutMapping("/{id}")
    public ApiResponse<JobResponse> updateJob(@PathVariable Long id, @RequestBody JobUpdateRequest request) {
        return ApiResponse.<JobResponse>builder().result(jobService.updateJob(id, request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ApiResponse.<Void>builder().build();
    }
}
