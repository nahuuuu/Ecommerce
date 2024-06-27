package com.ecommerce.service.interfaces;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.entity.ProductEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService {

    void createProduct(ProductRequest productRequest, Authentication authentication);

    List<ProductDTO> searchProductsByTitle(String productName);

    ProductDTO updateProduct(Long id, ProductEntity productEntity);

    String deleteProduct(Long id);

    List<ProductDTO> getAllProducts(Pagination pagination, Authentication authentication );


}
