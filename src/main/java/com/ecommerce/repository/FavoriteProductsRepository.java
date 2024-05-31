package com.ecommerce.repository;

import com.ecommerce.entity.FavoriteProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteProductsRepository extends JpaRepository<FavoriteProductsEntity, Long> {
}
