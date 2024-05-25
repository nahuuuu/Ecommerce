package com.ecommerce.repository;

import com.ecommerce.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    List<PurchaseEntity> findByUsername(String username);
}
