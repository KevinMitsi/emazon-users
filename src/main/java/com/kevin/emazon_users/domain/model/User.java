package com.kevin.emazon_users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String identificationNumber;
    private String mobileNumber;
    private LocalDate date;
    private String email;
    private String password;
    private Long role;

    public User() {
        //for frameworks etc...
    }

}
