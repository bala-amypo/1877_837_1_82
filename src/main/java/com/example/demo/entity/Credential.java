// src/main/java/com/example/demo/entity/Credential.java
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "credentials")
@Data
public class Credential {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @Column(unique = true)
    private String credentialId;

    private String issuer;
    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;
    private String status;
    private String metadataJson;
}
