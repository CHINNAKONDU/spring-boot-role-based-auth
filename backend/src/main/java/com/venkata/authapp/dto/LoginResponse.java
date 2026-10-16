package com.venkata.authapp.dto;

public record LoginResponse(String token, String username, String role) {
}
