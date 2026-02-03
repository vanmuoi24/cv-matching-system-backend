package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.response.ApplicationInfoResponse;
import com.example.smartcv_backend.entity.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                UserMapper.class
        })
public interface ApplicationInfoMapper {
    ApplicationInfoResponse toApplicationResponse(Application application);
}
