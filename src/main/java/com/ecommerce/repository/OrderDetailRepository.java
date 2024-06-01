package com.ecommerce.repository;

import com.ecommerce.entity.CartEntity;
import com.ecommerce.entity.OrderDetailEntity;
import com.ecommerce.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Stream;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
   List<OrderDetailEntity> findByPurchase(PurchaseEntity purchase);
}
