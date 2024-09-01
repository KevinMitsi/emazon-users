package com.kevin.emazon_users.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kevin.emazon_users.domain.model.RoleEnum;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

}
