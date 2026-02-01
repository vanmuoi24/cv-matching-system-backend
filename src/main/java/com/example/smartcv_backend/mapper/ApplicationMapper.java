package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.request.ApplicationCreateRequest;
import com.example.smartcv_backend.dto.request.ApplicationUpdateRequest;
import com.example.smartcv_backend.dto.response.ApplicationResponse;
import com.example.smartcv_backend.entity.Application;
import com.example.smartcv_backend.entity.CandidateProfile;
import com.example.smartcv_backend.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {
                JobMapper.class,
                UserInfoMapper.class
})
public interface ApplicationMapper {
        Application toApplication(ApplicationCreateRequest request);

        ApplicationResponse toApplicationResponse(Application application);

        java.util.List<ApplicationResponse> toApplicationResponseList(java.util.Set<Application> applications);

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateApplicationFromRequest(ApplicationUpdateRequest request, @MappingTarget Application application);
}
