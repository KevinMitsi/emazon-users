package com.kevin.emazon_users.infraestructure.adapters;

import com.kevin.emazon_users.domain.model.User;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.infraestructure.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistentPort {
    private IUserRepository userRepository;
    @Override
    public void saveUser(User user) {
        //no yet
    }
}
