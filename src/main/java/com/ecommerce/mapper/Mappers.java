package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.UserDTO;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;
import com.ecommerce.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;


public class Mappers {


    public static UserDTO userToUserDto(UserEntity user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()

                //user.getPurchase()
        );
    }

    public static List<ProductDTO> productToProductDTO(List<ProductEntity> products){


        return products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getRating(),
                product.getStock(),
                false
                )).collect(Collectors.toList());



    }

    public static void updateUser(UserEntity oldUser, UserUpdateRequest updatedUser){
        oldUser.setUsername(updatedUser.username());
        oldUser.setEmail(updatedUser.email());
    }




}
