package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderdetail")
public class OrderDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The quantity of products cannot be zero")
    private Integer quantity;

    @NotNull(message = "The price of products cannot be zero")
    private Float totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity odProduct;

    @ManyToOne
    @JoinColumn(name = "id_purchase")
    private PurchaseEntity purchase;



}
