package com.kevin.emazon_users.domain.spi;

import com.kevin.emazon_users.domain.model.UserModel;

import java.util.Optional;

public interface IUserPersistentPort {
    void saveUser(UserModel userModel);
    boolean exist(String identificationNumber, String email);
    Optional<UserModel> findByUsername(String username);
}
