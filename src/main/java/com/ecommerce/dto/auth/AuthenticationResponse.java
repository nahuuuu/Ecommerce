package com.ecommerce.dto.auth;

public record AuthenticationResponse(String jwt, String error) {
}
