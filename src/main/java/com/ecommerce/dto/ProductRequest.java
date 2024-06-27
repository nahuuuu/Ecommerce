package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public record ProductRequest(

        @NotBlank(message = "The name of the product is missing")
        String name,
        @NotBlank(message = "The product description cant be empty")
        String description,

        @NotNull(message = "price cant be null")
        BigDecimal price,

        @Min(value = 0, message = "Stock cant be less than 0")
        Integer stock,

        List<MultipartFile> image



) {
}
