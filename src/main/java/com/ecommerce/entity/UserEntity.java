package com.ecommerce.entity;

import com.ecommerce.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
    @NotBlank(message = "La contraseña no puede estar vacia")
    @Column(nullable = false)
    private String password;

    @Email(message = "El formato del email es incorrecto")
    @NotBlank(message = "El email no puede estar vacio")
    @Column(nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> role = new HashSet<>();

    // own_cart - one user can have only one cart (wich one can be updated on the db)
    @OneToOne(mappedBy = "cartOwner", cascade = CascadeType.ALL)
    private CartEntity cart;

    // Fav products
    @OneToOne(mappedBy = "favListOwner")
    private FavoriteProductsEntity favProductsList;

    // purchase
    @OneToMany(mappedBy = "user")
    private List<PurchaseEntity> purchases;

    //published products
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleEntity> setRole = getRole();

        //arreglar
        RoleEntity role = setRole.stream().findFirst().orElse(new RoleEntity(Role.GUEST));


        List<GrantedAuthority> authorities = role.getRole().getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("Role_" + role.getRole().name()));

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
