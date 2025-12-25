// src/main/java/com/example/demo/dto/CredentialStatusDto.java
package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CredentialStatusDto {
    private String credentialId;
    private String status;
    private LocalDateTime verifiedAt;
    private String details;
}
