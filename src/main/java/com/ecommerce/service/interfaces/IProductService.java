package com.ecommerce.service.interfaces;

import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductDTO getProduct(Long id);

    ProductDTO updateProduct(Long id, ProductEntity productEntity);

    String deleteProduct(Long id);

    List<ProductDTO> getAllProducts();
}
