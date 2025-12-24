// src/main/java/com/example/demo/entity/CredentialVerificationEvent.java
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credential_verification_events")
public class CredentialVerificationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to Credential
    @ManyToOne(optional = false)
    @JoinColumn(name = "credential_id")
    private Credential credential;

    @Column(nullable = false)
    private LocalDateTime verifiedAt;

    @Column(nullable = false)
    private String result; // SUCCESS or FAILURE

    @Lob
    private String details;

    public CredentialVerificationEvent() {}

    public CredentialVerificationEvent(Credential credential, LocalDateTime verifiedAt, String result, String details) {
        this.credential = credential;
        this.verifiedAt = verifiedAt;
        this.result = result;
        this.details = details;
    }

    // Getters and setters...
}
