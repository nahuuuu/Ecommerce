package com.ecommerce.repository;

import com.ecommerce.entity.RoleEntity;
import com.ecommerce.util.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


    RoleEntity findByrole(Role name);
}
