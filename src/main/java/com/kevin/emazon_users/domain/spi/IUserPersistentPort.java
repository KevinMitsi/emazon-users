package com.kevin.emazon_users.domain.spi;

import com.kevin.emazon_users.domain.model.User;

public interface IUserPersistentPort {
    void saveUser(User user);
}
