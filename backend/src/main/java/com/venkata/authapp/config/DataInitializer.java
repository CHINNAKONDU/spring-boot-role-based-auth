package com.venkata.authapp.config;

import com.venkata.authapp.model.Role;
import com.venkata.authapp.model.User;
import com.venkata.authapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    // Creates two demo accounts on startup (passwords are stored as BCrypt hashes)
    @Bean
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new User("user", encoder.encode("user123"), Role.USER));
                repo.save(new User("admin", encoder.encode("admin123"), Role.ADMIN));
            }
        };
    }
}
