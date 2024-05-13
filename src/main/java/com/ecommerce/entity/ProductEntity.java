package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Float price;

    @Column
    private Float rating;

    @OneToMany(mappedBy = "products")
    private List<OrderDetailEntity> cart;

// TODO: 4/5/2024
// implementar relacion con usuario, categoria. añadir columna stock, rating, etc.
    //private UserEntity userEntity;


}
