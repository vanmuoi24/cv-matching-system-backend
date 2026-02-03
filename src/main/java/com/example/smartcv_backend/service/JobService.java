package com.example.smartcv_backend.service;

import com.example.smartcv_backend.dto.request.JobCreateRequest;
import com.example.smartcv_backend.dto.request.JobUpdateRequest;
import com.example.smartcv_backend.dto.response.JobInfoResponse;
import com.example.smartcv_backend.dto.response.JobResponse;
import com.example.smartcv_backend.entity.Company;
import com.example.smartcv_backend.entity.Job;
import com.example.smartcv_backend.entity.User;
import com.example.smartcv_backend.exception.AppException;
import com.example.smartcv_backend.exception.ErrorCode;
import com.example.smartcv_backend.mapper.JobInfoMapper;
import com.example.smartcv_backend.mapper.JobMapper;
import com.example.smartcv_backend.repository.CompanyRepository;
import com.example.smartcv_backend.repository.JobRepository;
import com.example.smartcv_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobService {
    private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JobService.class);
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobInfoMapper jobInfoMapper;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public JobResponse createJob(JobCreateRequest request) {
        if (request.getCreatedById() == null) {
            throw new AppException(ErrorCode.User_name);
        }
        if (request.getCompanyId() == null) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        Job job = jobMapper.toJob(request);

        User user = userRepository.findById(request.getCreatedById())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_KEY));

        job.setCreatedBy(user);
        job.setCompany(company);
        job.setCreateAt(LocalDateTime.now());
        if (job.getStatus() == null) {
            job.setStatus("OPEN");
        }

        try {
            job = jobRepository.save(job);
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        return jobMapper.toJobResponse(job);
    }

    public JobInfoResponse getJobById(Long id) {
        return jobRepository.findById(id)
                .map(jobInfoMapper::toJobInfoResponse)
                .orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));
    }

    public List<JobResponse> getAllJob() {
        return jobRepository.findAll().stream().map(jobMapper::toJobResponse).collect(Collectors.toList());
    }

    @Transactional
    public JobResponse updateJob(Long id, JobUpdateRequest req) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));

        jobMapper.updateJobFromRequest(req, job);

        jobRepository.save(job);

        return jobMapper.toJobResponse(job);
    }

    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.JOB_NOT_EXISTED));
        jobRepository.delete(job);
    }
}
