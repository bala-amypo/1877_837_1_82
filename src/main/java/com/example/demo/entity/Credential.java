// src/main/java/com/example/demo/entity/Credential.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credentials", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"credentialId"})
})
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to EmployeeProfile
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeProfile employee;

    @Column(nullable = false)
    private String credentialId;

    @Column(nullable = false)
    private String issuer;

    @Column(nullable = false)
    private LocalDateTime issuedAt;

    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private String status; // PENDING, VERIFIED, REVOKED, EXPIRED

    @Lob
    private String metadataJson;

    public Credential() {}

    public Credential(EmployeeProfile employee, String credentialId, String issuer,
                      LocalDateTime issuedAt, LocalDateTime expiresAt, String status, String metadataJson) {
        this.employee = employee;
        this.credentialId = credentialId;
        this.issuer = issuer;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.status = status;
        this.metadataJson = metadataJson;
    }

    // Getters and setters...
}
