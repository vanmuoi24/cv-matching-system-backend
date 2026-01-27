package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.request.ApplicationCreateRequest;
import com.example.smartcv_backend.dto.request.ApplicationUpdateRequest;
import com.example.smartcv_backend.dto.response.ApplicationInfoResponse;
import com.example.smartcv_backend.dto.response.ApplicationResponse;
import com.example.smartcv_backend.dto.response.JobInfoResponse;
import com.example.smartcv_backend.entity.Application;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                UserMapper.class
        })
public interface ApplicationInfoMapper {
    ApplicationInfoResponse toApplicationResponse(Application application);
}
