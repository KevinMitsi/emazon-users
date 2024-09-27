package com.kevin.emazon_users.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserModel  {

    private Long id;
    private String name;
    private String surname;
    private String identificationNumber;
    private String mobileNumber;
    private Date date;
    private String email;
    private String password;
    private RoleEnum role;

    public UserModel() {
        // for frameworks etc...
    }

}
