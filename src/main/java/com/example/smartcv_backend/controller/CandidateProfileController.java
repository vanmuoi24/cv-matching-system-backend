package com.example.smartcv_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartcv_backend.dto.request.ApplicationCreateRequest;
import com.example.smartcv_backend.dto.request.ApplicationUpdateRequest;
import com.example.smartcv_backend.dto.request.CandidateProfileCreateRequest;
import com.example.smartcv_backend.dto.request.CandidateProfileUpdateRequest;
import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.CandidateProfileResponse;
import com.example.smartcv_backend.service.CandidateProfileService;

import jakarta.websocket.server.PathParam;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/candidate-profiles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CandidateProfileController {

    CandidateProfileService candidateProfileService;

    @GetMapping("/{id}")
    public ApiResponse<CandidateProfileResponse> getCandidateProfileById(@PathVariable Long id) {
        return ApiResponse.<CandidateProfileResponse>builder()
                .result(candidateProfileService.getCandidateProfileById(id)).build();
    }

    @GetMapping("/list")
    public ApiResponse<List<CandidateProfileResponse>> getAllCandidateProfile() {
        return ApiResponse.<List<CandidateProfileResponse>>builder()
                .result(candidateProfileService.getAllCandidateProfiles())
                .build();
    }

    @PostMapping(consumes = { "multipart/form-data" })
    ApiResponse<CandidateProfileResponse> createCandidateProfile(
            @ModelAttribute CandidateProfileCreateRequest request) {
        var result = candidateProfileService.createCandidateProfile(request);
        return ApiResponse.<CandidateProfileResponse>builder().result(result).build();
    }

    @PostMapping("/update/{id}")
    public ApiResponse<CandidateProfileResponse> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateProfileUpdateRequest request) {

        return ApiResponse.<CandidateProfileResponse>builder()
                .result(candidateProfileService.updateCandidateProfile(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCandidateProfile(@PathVariable Long id) {
        candidateProfileService.deleteCandidateProfile(id);
        return ApiResponse.<Void>builder().build();
    }
}
