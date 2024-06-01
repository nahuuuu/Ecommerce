package com.ecommerce.service.impl;

import com.ecommerce.dto.OrderDetailDTO;
import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserServiceImpl userService;


    @Override
    public ProductDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductEntity productEntity) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts(Pagination pagination, Authentication authentication ){
        //TODO  change the findByUsername for findById

        UserEntity currentUser = userRepository.findByUsername(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        Pageable pageable = PageRequest.of(pagination.page(), pagination.size());

        Page<ProductEntity> pageProduct = productRepository.findAll(pageable);


        List<ProductDTO> productsOnCart = Mappers.productToProductDTO(productRepository.findByCart(currentUser.getCart()));

        List<ProductDTO> products = Mappers.productToProductDTO(pageProduct.getContent());

        products.stream().filter(product -> productsOnCart.stream().anyMatch(
                productOnCart -> productOnCart.getId() == product.getId()
        )).forEach(product -> product.setInCart(true));

        return products;

    }
}
