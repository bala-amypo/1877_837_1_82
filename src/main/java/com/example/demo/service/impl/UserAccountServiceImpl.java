// src/main/java/com/example/demo/service/impl/UserAccountServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.entity.UserAccount;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserAccountServiceImpl(UserAccountRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserAccount registerUser(UserAccount user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole(Set.of("EMPLOYEE"));
        }
        return userRepo.save(user);
    }

    @Override
    public UserAccount findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserAccount findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
