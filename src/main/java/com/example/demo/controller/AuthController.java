package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.service.UserAccountService;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserAccountService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserAccountService userService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public UserAccount register(@RequestBody UserAccount user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAccount user) {
        UserAccount existing = userService.findByEmail(user.getEmail());
        return jwtTokenProvider.generateToken(existing.getEmail());
    }
}
