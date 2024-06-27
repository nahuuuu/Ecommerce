package com.ecommerce.service.impl;

import com.ecommerce.dto.Pagination;
import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.entity.ImageEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.Mappers;
import com.ecommerce.repository.ImageRepository;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.interfaces.IProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Mappers mappers;

    @Override
    public void createProduct(ProductRequest productRequest , Authentication authentication) {

        List<ImageEntity> images = productRequest.image().stream().map(imageService::imageUpload).toList();

        UserEntity currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("The user was not found")
        );

        ProductEntity product = Mappers.productRequestToProductEntity(productRequest, images);

        product.setUser(currentUser);
        currentUser.getProducts().add(product);
        images.forEach(currentImage -> currentImage.setProduct(product));
        userRepository.save(currentUser);

    }

    @Override
    public List<ProductDTO> searchProductsByTitle(String productName) {

        List<ProductEntity> products = productRepository.findByTitleContainingIgnoreCase(productName);

        return mappers.productToProductDTO(products);
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

        if (authentication instanceof AnonymousAuthenticationToken){

            Pageable pageable = PageRequest.of(pagination.page(), pagination.size());

            Page<ProductEntity> pageProduct = productRepository.findAll(pageable);
            return mappers.productToProductDTO(pageProduct.getContent());
        }

        UserEntity currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(
                () -> new ResourceNotFoundException("user not found")
        );

        Pageable pageable = PageRequest.of(pagination.page(), pagination.size());

        Page<ProductEntity> pageProduct = productRepository.findAll(pageable);


        List<ProductDTO> productsOnCart = mappers.productToProductDTO(productRepository.findByCart(currentUser.getCart()));

        List<ProductDTO> products = mappers.productToProductDTO(pageProduct.getContent());

        products.stream().filter(product -> productsOnCart.stream().anyMatch(
                productOnCart -> productOnCart.getId() == product.getId()
        )).forEach(product -> product.setInCart(true));

        return products;

    }

}
