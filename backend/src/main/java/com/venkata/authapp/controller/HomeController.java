package com.venkata.authapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {

    // Any logged-in user (USER or ADMIN) can open this
    @GetMapping("/home")
    public Map<String, String> home(Authentication authentication) {
        return Map.of(
                "message", "Welcome, " + authentication.getName() + "!",
                "username", authentication.getName());
    }
}
