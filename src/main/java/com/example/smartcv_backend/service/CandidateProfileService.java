package com.example.smartcv_backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smartcv_backend.dto.request.CandidateProfileCreateRequest;
import com.example.smartcv_backend.dto.request.CandidateProfileUpdateRequest;
import com.example.smartcv_backend.dto.response.CandidateProfileResponse;
import com.example.smartcv_backend.entity.CandidateProfile;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.CandidateProfileMapper;
import com.example.smartcv_backend.repository.CandidateProfileRepository;
import com.example.smartcv_backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateProfileService {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CandidateProfileService.class);
    private final CandidateProfileRepository candidateProfileRepository;
    private final CandidateProfileMapper candidateProfileMapper;
    private final UserRepository userRepository;

    public CandidateProfileResponse getCandidateProfileById(Long id){
        return candidateProfileRepository.findById(id)
                .map(candidateProfileMapper::toCandidateProfileResponse)
                .orElseThrow(() -> {
                    return new AppException(ErrorCode.CANDIDATE_PROFILE_NOT_EXISTED);
                });
    }

    public List<CandidateProfileResponse> getAllCandidateProfiles(){
        return candidateProfileRepository.findAll()
                .stream()
                .map(candidateProfileMapper::toCandidateProfileResponse)
                .toList();
    }

    public CandidateProfileResponse createCandidateProfile(CandidateProfileCreateRequest request){
            //check userId đã truyền chưa
                    log.info("Creating candidate profile for userId: {}", request.getUserId());

        if (request.getUserId() == null){
        // log.info("Creating candidate profile for userId: {}", request.getUserId());
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        log.info("Creating candidate profile for userId: {}", user.getId());

        CandidateProfile profile = new CandidateProfile();
        profile.setUser(user);   // 🔥 BẮT BUỘC
        profile.setExperienceYear(request.getExperienceYear());
        profile.setSkills(request.getSkills());
        profile.setSummary(request.getSummary());
        profile.setCvText(request.getCvText());
        profile.setCvFileUrl(request.getCvFileUrl());
        profile.setUpdateAt(LocalDateTime.now());

        profile = candidateProfileRepository.save(profile);
        return candidateProfileMapper.toCandidateProfileResponse(profile);
    }

    public CandidateProfileResponse updateCandidateProfile(Long id, CandidateProfileUpdateRequest request){
        var candidateProfile = candidateProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_PROFILE_NOT_EXISTED));
        candidateProfile.setUpdateAt(LocalDateTime.now());
        candidateProfileMapper.updateCandidateProfileFromRequest(request, candidateProfile);
        candidateProfile = candidateProfileRepository.save(candidateProfile);
        return candidateProfileMapper.toCandidateProfileResponse(candidateProfile);
    }

    public void deleteCandidateProfile(Long id){
        var candidateProfile = candidateProfileRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CANDIDATE_PROFILE_NOT_EXISTED));
        candidateProfileRepository.delete(candidateProfile);
    }
 }
