// src/main/java/com/example/demo/service/impl/CredentialVerificationServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.entity.Credential;
import com.example.demo.entity.CredentialVerificationEvent;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CredentialRepository;
import com.example.demo.repository.CredentialVerificationEventRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CredentialVerificationServiceImpl implements CredentialVerificationService {

    private final CredentialRepository credentialRepo;
    private final CredentialVerificationEventRepository eventRepo;
    private final EmployeeProfileRepository employeeRepo;

    public CredentialVerificationServiceImpl(
            CredentialRepository credentialRepo,
            CredentialVerificationEventRepository eventRepo,
            EmployeeProfileRepository employeeRepo
    ) {
        this.credentialRepo = credentialRepo;
        this.eventRepo = eventRepo;
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Credential registerCredential(Credential credential) {
        Long employeeId = credential.getEmployee().getId();
        EmployeeProfile employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalStateException("Employee not active");
        }
        // Uniqueness
        credentialRepo.findByCredentialId(credential.getCredentialId()).ifPresent(c -> {
            throw new IllegalStateException("Credential mapping exists");
        });
        if (credential.getStatus() == null) {
            credential.setStatus("PENDING");
        }
        if (credential.getIssuedAt() == null) {
            credential.setIssuedAt(LocalDateTime.now());
        }
        return credentialRepo.save(credential);
    }

    @Override
    public CredentialStatusDto verifyCredential(String credentialId) {
        Credential credential = credentialRepo.findByCredentialId(credentialId)
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));

        // Expired or revoked
        if ("REVOKED".equalsIgnoreCase(credential.getStatus())) {
            throw new IllegalStateException("Credential revoked");
        }
        if (credential.getExpiresAt() != null && credential.getExpiresAt().isBefore(LocalDateTime.now())) {
            credential.setStatus("EXPIRED");
            credentialRepo.save(credential);
            throw new IllegalStateException("Credential expired");
        }

        // Verify and update status
        credential.setStatus("VERIFIED");
        credentialRepo.save(credential);

        CredentialVerificationEvent event = new CredentialVerificationEvent(
                credential,
                LocalDateTime.now(),
                "SUCCESS",
                "Verification successful"
        );
        eventRepo.save(event);

        CredentialStatusDto dto = new CredentialStatusDto();
        dto.setCredentialId(credential.getCredentialId());
        dto.setStatus(credential.getStatus());
        dto.setVerifiedAt(event.getVerifiedAt());
        dto.setDetails(event.getDetails());
        return dto;
    }

    @Override
    public List<Credential> getCredentialsForEmployee(Long employeeId) {
        return credentialRepo.findByEmployee_Id(employeeId);
    }
}
