package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.request.CompanyCreateRequest;
import com.example.smartcv_backend.dto.request.CompanyUpdateRequest;
import com.example.smartcv_backend.dto.response.CompanyResponse;
import com.example.smartcv_backend.entity.Company;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                JobMapper.class
        })
public interface CompanyMapper {
    Company toCompany(CompanyCreateRequest request);
    CompanyResponse toUserResponse(Company company);
    java.util.List<CompanyResponse> toUserResponseList(java.util.Set<Company> companys);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCompanyFromRequest(CompanyUpdateRequest request, @MappingTarget Company company);
}
