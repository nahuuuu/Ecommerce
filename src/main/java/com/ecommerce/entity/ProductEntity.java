
package com.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "products")
public class ProductEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;

    private String description;

    private BigDecimal price;

    private Float rating;

    private Integer stock;

    @JsonIgnore
    @ManyToMany(mappedBy = "cartProducts")
    private List<CartEntity> cart;

    @JsonIgnore
    @OneToMany(mappedBy = "odProduct")
    private List<OrderDetailEntity> orderDetail;
    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<FavoriteProductsEntity> favProductsList;



    // implementar relacion con usuario, categoria. añadir columna stock, rating, etc.
    //private UserEntity userEntity;


}
