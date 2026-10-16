package com.venkata.authapp.controller;

import com.venkata.authapp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Only users with the ADMIN role can open this (see SecurityConfig)
    @GetMapping("/dashboard")
    public Map<String, Object> dashboard(Authentication authentication) {
        return Map.of(
                "message", "Admin dashboard for " + authentication.getName(),
                "totalUsers", userRepository.count());
    }
}
