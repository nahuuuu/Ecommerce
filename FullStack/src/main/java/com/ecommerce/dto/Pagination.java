package com.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Pagination(
        @NotNull(message = "This field cannot be null")
        @Min(value = 0, message = "The value must be greater than or equal to zero")
        Integer page,

        @NotNull(message = "This field cannot be null")
        @Min(value = 0, message = "The value must be greater than or equal to one")
        Integer size
) {
}
