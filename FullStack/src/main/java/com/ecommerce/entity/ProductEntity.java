package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

// TODO: 4/5/2024
// implementar relacion con usuario, categoria. añadir columna stock, rating, etc.
    //private UserEntity userEntity;


}
