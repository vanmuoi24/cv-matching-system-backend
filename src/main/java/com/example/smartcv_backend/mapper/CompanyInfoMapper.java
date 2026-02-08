package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.response.CompanyInfoResponse;
import com.example.smartcv_backend.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyInfoMapper {
    CompanyInfoResponse toUserResponse(Company company);
}
