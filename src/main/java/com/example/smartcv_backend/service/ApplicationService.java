package com.example.smartcv_backend.service;

import com.example.smartcv_backend.dto.request.ApplicationCreateRequest;
import com.example.smartcv_backend.dto.request.ApplicationUpdateRequest;
import com.example.smartcv_backend.dto.request.UserUpdateRequest;
import com.example.smartcv_backend.dto.response.ApplicationResponse;
import com.example.smartcv_backend.dto.response.UserResponse;
import com.example.smartcv_backend.entity.Application;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.ApplicationMapper;
import com.example.smartcv_backend.repository.ApplicationRepository;
import com.example.smartcv_backend.repository.JobRepository;
import com.example.smartcv_backend.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ApplicationService.class);
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationResponse createApplication(ApplicationCreateRequest request){
        //check userId đã truyền chưa
        if (request.getCandidateId() == null){
            throw new AppException(ErrorCode.User_name);
        }

        //check đang apply cho job nào
        if (request.getJobId() == null) {
            throw new AppException(ErrorCode.INVALID_JOB);
        }

        Application application = applicationMapper.toApplication(request);
        application.setCandidate(userRepository.findById(request.getCandidateId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
        application.setJob(jobRepository.findById(request.getJobId()).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED)));
        application.setStatus("SUBMITTED");
        application.setAppliedAt(LocalDateTime.now());
        try {
            applicationRepository.save(application);
        } catch (DataIntegrityViolationException e){
            throw new AppException(ErrorCode.APPLICATION_EXISTED);
        }
        return applicationMapper.toApplicationResponse(application);
    }

    public ApplicationResponse getApplicationById(Long id){
        return applicationRepository.findById(id).map(applicationMapper :: toApplicationResponse)
        .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_EXISTED));
    }

    public List<ApplicationResponse> getAllApplication(){
        return applicationRepository.findAll().stream().map(applicationMapper::toApplicationResponse).collect(Collectors.toList());
    }

    @Transactional
    public ApplicationResponse updateApplication(Long id, ApplicationUpdateRequest req) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_EXISTED));
        // log.info("Creating application for candidateId: {} for jobId: {} for id : {}", application.getCandidate(), application.getJob(), id);

        Optional.ofNullable(req.getSimilarityScore()).ifPresent(application::setSimilarityScore);
        Optional.ofNullable(req.getStatus()).ifPresent(application::setStatus);
        // log.info("Creating application for candidateId: {} for jobId: {} for id : {}", req.getSimilarityScore(), application.getStatus(), id);

        applicationRepository.save(application);

        return applicationMapper.toApplicationResponse(application);
    }

    public void deleteApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.APPLICATION_NOT_EXISTED));
        applicationRepository.delete(application);
    }
}
