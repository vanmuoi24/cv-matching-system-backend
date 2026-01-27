package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.response.JobInfoResponse;
import com.example.smartcv_backend.dto.response.JobResponse;
import com.example.smartcv_backend.entity.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                UserInfoMapper.class,
                CompanyInfoMapper.class
        })
public interface JobInfoMapper {
    JobInfoResponse toJobInfoResponse (Job job);
}
