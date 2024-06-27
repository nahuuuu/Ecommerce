package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.ImageEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.OrderDetailRepository;
import com.ecommerce.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class Mappers {

    @Autowired
    private ImageServiceImpl imageService;

    public static UserDTO userToUserDto(UserEntity user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()

                //user.getPurchase()
        );
    }

    @Bean
    public List<ProductDTO> productToProductDTO(List<ProductEntity> products){


        return products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                false,
                imageService.getProductImages(product)


                )).collect(Collectors.toList());



    }

    public static ProductEntity productRequestToProductEntity(ProductRequest request, List<ImageEntity> images){
        return ProductEntity.builder()
                .title(request.name())
                .price(request.price())
                .description(request.description())
                .stock(request.stock())
                .image(images)
                .build();

    }

    public static void updateUser(UserEntity oldUser, UserUpdateRequest updatedUser){
        oldUser.setUsername(updatedUser.username());
        oldUser.setEmail(updatedUser.email());
    }




}
