package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.request.CandidateProfileCreateRequest;
import com.example.smartcv_backend.dto.request.CandidateProfileUpdateRequest;
import com.example.smartcv_backend.dto.response.CandidateProfileResponse;
import com.example.smartcv_backend.entity.CandidateProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                UserMapper.class
        }
)
public interface CandidateProfileMapper {
    CandidateProfile toCandidateProfile(CandidateProfileCreateRequest request);
    CandidateProfileResponse toUserResponse(CandidateProfile candidateProfile);
    java.util.List<CandidateProfileResponse> toUserResponseList(java.util.Set<CandidateProfile> candidateProfiles);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCandidateProfileFromRequest(CandidateProfileUpdateRequest request, @MappingTarget CandidateProfile candidateProfile);
}
