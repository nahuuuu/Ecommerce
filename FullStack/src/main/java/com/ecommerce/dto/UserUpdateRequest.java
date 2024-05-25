package com.ecommerce.dto;

public record UserUpdateRequest(
        String username,
        String email
) {
}
