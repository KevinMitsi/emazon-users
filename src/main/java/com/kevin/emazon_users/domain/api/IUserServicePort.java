package com.kevin.emazon_users.domain.api;

import com.kevin.emazon_users.domain.model.UserModel;

public interface IUserServicePort {
    void saveUser(UserModel userModel);
}
