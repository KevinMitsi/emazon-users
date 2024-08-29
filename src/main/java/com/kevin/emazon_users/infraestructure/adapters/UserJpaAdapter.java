package com.kevin.emazon_users.infraestructure.adapters;

import com.kevin.emazon_users.domain.model.User;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.infraestructure.mapper.IUserEntityMapper;
import com.kevin.emazon_users.infraestructure.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class UserJpaAdapter implements IUserPersistentPort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toUserEntity(user));
    }

    @Override
    public boolean exist(String identificationNumber, String email) {
        return userRepository.existsByIdentificationNumberOrEmail(identificationNumber, email);
    }
}
