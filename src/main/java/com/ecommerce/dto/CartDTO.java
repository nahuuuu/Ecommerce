package com.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(List<ProductDTO> products, BigDecimal totalPrice) {
}
