package com.ecommerce.dto.auth;

public record AuthenticationRequest(String email, Long password) {
}
