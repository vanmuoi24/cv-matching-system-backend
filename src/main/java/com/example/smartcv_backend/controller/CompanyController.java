package com.example.smartcv_backend.controller;

import com.example.smartcv_backend.dto.request.CompanyCreateRequest;
import com.example.smartcv_backend.dto.request.CompanyUpdateRequest;
import com.example.smartcv_backend.dto.response.ApiResponse;
import com.example.smartcv_backend.dto.response.CompanyResponse;
import com.example.smartcv_backend.service.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyService companyService;

    @PostMapping
    public ApiResponse<CompanyResponse> createCompany(@RequestBody CompanyCreateRequest request) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.createCompany(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CompanyResponse>> getAllCompanies() {
        return ApiResponse.<List<CompanyResponse>>builder()
                .result(companyService.getAllCompanies())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.getCompanyById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CompanyResponse> updateCompany(@PathVariable Long id,
            @RequestBody CompanyUpdateRequest request) {
        return ApiResponse.<CompanyResponse>builder()
                .result(companyService.updateCompany(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ApiResponse.<Void>builder().build();
    }
}
