package com.kevin.emazon_users.domain.api;

import com.kevin.emazon_users.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
}
