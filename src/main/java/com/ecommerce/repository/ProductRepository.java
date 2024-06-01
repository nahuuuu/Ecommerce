package com.ecommerce.repository;

import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByCart(CartEntity cartEntity);
}
