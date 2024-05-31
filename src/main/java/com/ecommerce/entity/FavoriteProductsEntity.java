package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorites")
public class FavoriteProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity favListOwner;

    @ManyToMany
    @JoinTable(name = "favorites_product", joinColumns = @JoinColumn(name = "favSection_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;


}
