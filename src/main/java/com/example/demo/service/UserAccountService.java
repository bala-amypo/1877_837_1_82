// src/main/java/com/example/demo/service/UserAccountService.java
package com.example.demo.service;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
    UserAccount registerUser(UserAccount user);
    UserAccount findByEmail(String email);
    UserAccount findById(Long id);
}
