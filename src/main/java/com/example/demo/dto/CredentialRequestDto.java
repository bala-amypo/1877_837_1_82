// src/main/java/com/example/demo/dto/CredentialRequestDto.java
package com.example.demo.dto;

import java.time.LocalDateTime;

public class CredentialRequestDto {
    private Long employeeId;
    private String credentialId;
    private String issuer;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String metadataJson;
    // Getters and setters...
}
