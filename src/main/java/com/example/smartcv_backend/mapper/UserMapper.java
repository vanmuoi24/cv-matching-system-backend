package com.example.smartcv_backend.mapper;


import com.example.smartcv_backend.dto.request.UserCreateRequest;
import com.example.smartcv_backend.dto.request.UserUpdateRequest;
import com.example.smartcv_backend.dto.response.UserResponse;
import com.example.smartcv_backend.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(
    componentModel = "spring",
    uses = {
        CandidateProfileMapper.class,
        JobInfoMapper.class,
        CompanyInfoMapper.class
    }
)
public interface UserMapper {

//    @Mapping(source = "fullName", target = "fullName")
//    @Mapping(source = "password", target = "password")
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);
    java.util.List<UserResponse> toUserResponseList(java.util.Set<User> users);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromRequest(UserUpdateRequest request, @MappingTarget User user);
}
