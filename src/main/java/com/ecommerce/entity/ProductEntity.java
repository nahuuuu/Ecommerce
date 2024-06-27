
package com.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Builder
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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImageEntity> image = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "cartProducts")
    private List<CartEntity> cart;

    @JsonIgnore
    @OneToMany(mappedBy = "odProduct")
    private List<OrderDetailEntity> orderDetail;

    @JsonIgnore
    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    private List<FavoriteProductsEntity> favProductsList;
    // implementar relacion con usuario, categoria. añadir columna stock, rating, etc.

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;


}
