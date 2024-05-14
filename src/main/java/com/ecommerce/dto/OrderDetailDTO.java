package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderDetailDTO(

        @NotNull(message = "id cannot be null")
        Long id,

        @NotBlank(message = "This field cannot be blank")
        String username,

        @NotNull(message = "This field cannot be null")
        @Min(value = 1, message = "The value must be greater than zero")
        Integer quantity,

        @NotNull(message = "This field cannot be null")
        @Min(value = 1, message = "The value must be greater than one")
        Double price,

        ProductDTO productDTO


)

{
}
