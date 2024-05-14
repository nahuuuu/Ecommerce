package com.ecommerce.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDTO(

        @NotNull(message = "The product id cannot be null")
        Long id,

        @NotBlank(message = "The product title cannot be blank")
        String title,

        @NotBlank(message = "The product title cannot be blank")
        String description,

        @NotNull
        @Min(value = 1, message = "The price must be at least 1 or greater")
        BigDecimal price,


        @NotNull
        @DecimalMin(value = "0.1", message = "The minimum value cannot be less than 0.1")
        @Max(value = 5, message = "The maximum value cannot be more than 5")
        Float rating,

        @NotNull
        Integer stock

        ) {
}
