package com.kevin.emazon_users.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String surname;
    private String identificationNumber;
    private String mobileNumber;
    private Date date;
    private String email;
    private String password;
    private Role role;

    public User() {}

    public User(Long id, String name, String surname, String identificationNumber, String mobileNumber, String email, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identificationNumber = identificationNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.role = role;
    }

}
