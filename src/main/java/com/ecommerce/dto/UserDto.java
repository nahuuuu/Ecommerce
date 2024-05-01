package com.ecommerce.dto;

import com.ecommerce.entity.RoleEntity;
import com.ecommerce.util.Role;

import java.util.NoSuchElementException;
import java.util.Set;

public record UserDto(Long id, String username, String email, String password, Set<RoleEntity> role) {


    public Role getRoles(){
        return role.stream().findFirst().orElseThrow(
                () -> new NoSuchElementException("Role Not Found"))
                .getRole();
    }
}
