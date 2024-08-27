package com.kevin.emazon_users.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

}
