package com.ecommerce.mapper;

import com.ecommerce.dto.UserDto;
import com.ecommerce.dto.UserUpdateRequest;
import com.ecommerce.entity.UserEntity;

public class Mappers {

    public static UserDto userToUserDto(UserEntity user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getPurchase()
        );
    }

    public static void updateUser(UserEntity oldUser, UserUpdateRequest updatedUser){
        oldUser.setUsername(updatedUser.username());
        oldUser.setEmail(updatedUser.email());
    }




}
