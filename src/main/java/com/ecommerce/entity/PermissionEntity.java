package com.ecommerce.entity;


import com.ecommerce.util.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@Data
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private Permission permissions;

    @ManyToMany(mappedBy = "permissions", cascade = CascadeType.ALL)
    private Set<RoleEntity> roles;

    public PermissionEntity(Permission permissions) {
        this.permissions = permissions;
    }
}
