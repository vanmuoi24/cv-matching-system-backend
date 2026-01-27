package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.request.JobCreateRequest;
import com.example.smartcv_backend.dto.request.JobUpdateRequest;
import com.example.smartcv_backend.dto.response.JobResponse;
import com.example.smartcv_backend.entity.Job;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                ApplicationMapper.class,
                UserInfoMapper.class
})
public interface JobMapper {
    Job toJob(JobCreateRequest request);
    JobResponse toJobResponse (Job job);
    java.util.List<JobResponse> toJobResponseList(java.util.Set<Job> jobs);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateJobFromRequest(JobUpdateRequest request, @MappingTarget Job job);
}
