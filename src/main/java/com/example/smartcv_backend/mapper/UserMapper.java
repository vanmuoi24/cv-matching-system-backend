package com.example.smartcv_backend.mapper;


import com.example.smartcv_backend.dto.request.UserCreateRequest;
import com.example.smartcv_backend.dto.request.UserUpdateRequest;
import com.example.smartcv_backend.dto.response.UserResponse;
import com.example.smartcv_backend.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        uses = {
                CompanyMapper.class,
                JobMapper.class,
                ApplicationMapper.class,
                CandidateProfileMapper.class
        }
        )

public interface UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);
    java.util.List<UserResponse> toUserResponseList(java.util.Set<User> users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);
}
