package com.kevin.emazon_users.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private String identificationNumber;
    private String mobileNumber;
    private Date date;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleEntity role;

}
