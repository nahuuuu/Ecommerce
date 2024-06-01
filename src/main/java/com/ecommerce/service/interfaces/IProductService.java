package com.ecommerce.service.interfaces;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.entity.ProductEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IProductService {
    ProductDTO createProduct(ProductRequestDTO productRequestDTO);

    ProductDTO getProduct(Long id);

    ProductDTO updateProduct(Long id, ProductEntity productEntity);

    String deleteProduct(Long id);

    List<ProductDTO> getAllProducts(Pagination pagination, Authentication authentication );
}
