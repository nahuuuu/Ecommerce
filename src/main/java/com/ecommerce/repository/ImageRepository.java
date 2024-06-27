package com.ecommerce.repository;

import com.ecommerce.entity.ImageEntity;
import com.ecommerce.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {


    Optional<List<ImageEntity>> findByProduct(ProductEntity product);
}
