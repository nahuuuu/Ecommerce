package com.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "user_table")
public class UserEntity {

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

    /*@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;*/


}
