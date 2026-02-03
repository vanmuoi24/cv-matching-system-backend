package com.example.smartcv_backend.service;

import com.example.smartcv_backend.dto.request.CompanyCreateRequest;
import com.example.smartcv_backend.dto.request.CompanyUpdateRequest;
import com.example.smartcv_backend.dto.response.CompanyResponse;
import com.example.smartcv_backend.entity.Company;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.CompanyMapper;
import com.example.smartcv_backend.repository.CompanyRepository;
import com.example.smartcv_backend.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyService {
    CompanyRepository companyRepository;
    UserRepository userRepository;
    CompanyMapper companyMapper;

    public CompanyResponse createCompany(CompanyCreateRequest request) {
        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Company company = companyMapper.toCompany(request);
        company.setOwner(owner);
        company.setCreateAt(LocalDateTime.now());
        company.setStatus("ACTIVE"); // Default status

        return companyMapper.toUserResponse(companyRepository.save(company));
    }

    public List<CompanyResponse> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public CompanyResponse getCompanyById(Long id) {
        return companyMapper.toUserResponse(companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED))); // Assuming error code exists
    }

    public CompanyResponse updateCompany(Long id, CompanyUpdateRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));

        companyMapper.updateCompanyFromRequest(request, company);

        if (request.getOwnerId() != null) {
            User owner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
            company.setOwner(owner);
        }

        return companyMapper.toUserResponse(companyRepository.save(company));
    }

    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMPANY_NOT_EXISTED));
        companyRepository.delete(company);
    }
}
