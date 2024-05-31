package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(

        @NotBlank(message = "The name of the product is missing")
        String name,

        @Min(value = 0, message = "Stock cant be less than 0")
        Integer stock,

        @NotBlank(message = "The product description cant be empty")
        String description,

        @NotNull(message = "price cant be null")
        Double price
) {
}
