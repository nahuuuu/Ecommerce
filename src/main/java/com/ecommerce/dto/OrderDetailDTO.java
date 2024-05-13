package com.ecommerce.dto;

public record OrderDetailDTO(
        Long id,
        String name,
        Integer quantity,
        Double price
) {
}
