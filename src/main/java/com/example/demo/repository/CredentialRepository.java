// src/main/java/com/example/demo/repository/CredentialRepository.java
package com.example.demo.repository;

import com.example.demo.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
    List<Credential> findByEmployee_Id(Long employeeId);
    Optional<Credential> findByCredentialId(String credentialId);
}
