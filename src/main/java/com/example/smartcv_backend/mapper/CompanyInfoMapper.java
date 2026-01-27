package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.response.CompanyInfoResponse;
import com.example.smartcv_backend.dto.response.CompanyResponse;
import com.example.smartcv_backend.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyInfoMapper {
    CompanyInfoResponse toUserResponse(Company company);
}
