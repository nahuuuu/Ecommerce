package com.ecommerce.mapper;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.dto.UserDto;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;


public class Mappers {

    public static UserDto userToUserDto(UserEntity user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()

                //user.getPurchase()
        );
    }

    public static List<ProductDTO> productToProductDTO(List<ProductEntity> products){


        List<ProductDTO> productDTO = products.stream().map(ProductDTO::new).collect(Collectors.toList());


        )
    }

    public static void updateUser(UserEntity oldUser, UserUpdateRequest updatedUser){
        oldUser.setUsername(updatedUser.username());
        oldUser.setEmail(updatedUser.email());
    }




}
