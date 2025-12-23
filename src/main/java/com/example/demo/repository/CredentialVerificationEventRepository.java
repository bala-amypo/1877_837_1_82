// src/main/java/com/example/demo/repository/CredentialVerificationEventRepository.java
package com.example.demo.repository;

import com.example.demo.entity.CredentialVerificationEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CredentialVerificationEventRepository extends JpaRepository<CredentialVerificationEvent, Long> {
    List<CredentialVerificationEvent> findByCredential_Id(Long credentialId);
}
