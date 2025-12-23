// src/main/java/com/example/demo/controller/CredentialVerificationController.java
package com.example.demo.controller;

import com.example.demo.dto.CredentialRequestDto;
import com.example.demo.dto.CredentialStatusDto;
import com.example.demo.entity.Credential;
import com.example.demo.entity.EmployeeProfile;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.CredentialVerificationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/credentials")
public class CredentialVerificationController {

    private final CredentialVerificationService credService;
    private final EmployeeProfileRepository employeeRepo;

    public CredentialVerificationController(CredentialVerificationService credService,
                                            EmployeeProfileRepository employeeRepo) {
        this.credService = credService;
        this.employeeRepo = employeeRepo;
    }

    @PostMapping
    public Credential register(@RequestBody CredentialRequestDto dto) {
        EmployeeProfile emp = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Credential c = new Credential(
                emp,
                dto.getCredentialId(),
                dto.getIssuer(),
                dto.getIssuedAt() != null ? dto.getIssuedAt() : LocalDateTime.now(),
                dto.getExpiresAt(),
                "PENDING",
                dto.getMetadataJson()
        );
        return credService.registerCredential(c);
    }

    @PostMapping("/{credentialId}/verify")
    public CredentialStatusDto verify(@PathVariable String credentialId) {
        return credService.verifyCredential(credentialId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Credential> byEmployee(@PathVariable Long employeeId) {
        return credService.getCredentialsForEmployee(employeeId);
    }

    @GetMapping("/{credentialId}")
    public Credential get(@PathVariable String credentialId) {
        return credService.getCredentialsForEmployee(-1L).stream()
                .filter(c -> credentialId.equals(c.getCredentialId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Credential not found"));
    }
}
