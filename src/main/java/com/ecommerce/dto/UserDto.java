package com.ecommerce.dto;

import com.ecommerce.entity.PurchaseEntity;
import com.ecommerce.entity.RoleEntity;
import com.ecommerce.util.Role;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public record UserDto(Long id, String username, String email, Set<RoleEntity> role /*List<PurchaseEntity> purchases*/) {


    public Role getRoles(){
        return role.stream().findFirst().orElseThrow(
                () -> new NoSuchElementException("Role Not Found"))
                .getRole();
    }
}
