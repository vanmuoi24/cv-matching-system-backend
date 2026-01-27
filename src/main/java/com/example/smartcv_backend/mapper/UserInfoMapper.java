package com.example.smartcv_backend.mapper;

import com.example.smartcv_backend.dto.response.UserInfoResponse;
import com.example.smartcv_backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
        // uses = {
        //         CandidateProfileMapper.class,
        // }
)
public interface UserInfoMapper {
    UserInfoResponse toUserInfoResponse(User user);
}
