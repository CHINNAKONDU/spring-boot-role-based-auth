package com.venkata.authapp.controller;

import com.venkata.authapp.dto.LoginRequest;
import com.venkata.authapp.dto.LoginResponse;
import com.venkata.authapp.model.Role;
import com.venkata.authapp.model.User;
import com.venkata.authapp.repository.UserRepository;
import com.venkata.authapp.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody LoginRequest request) {
        return authenticate(request, Role.USER);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@Valid @RequestBody LoginRequest request) {
        return authenticate(request, Role.ADMIN);
    }

    private ResponseEntity<?> authenticate(LoginRequest request, Role requiredRole) {
        Optional<User> found = userRepository.findByUsername(request.username());

        if (found.isEmpty()
                || !passwordEncoder.matches(request.password(), found.get().getPassword())) {
            return ResponseEntity.status(401)
                    .body(Map.of("message", "Invalid username or password"));
        }

        User user = found.get();

        if (user.getRole() != requiredRole) {
            String message = requiredRole == Role.ADMIN
                    ? "This account is not an admin account"
                    : "Admin accounts must use the Admin login";
            return ResponseEntity.status(403).body(Map.of("message", message));
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getRole().name()));
    }
}
