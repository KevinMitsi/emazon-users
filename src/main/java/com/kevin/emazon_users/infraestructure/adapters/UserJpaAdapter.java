package com.kevin.emazon_users.infraestructure.adapters;

import com.kevin.emazon_users.domain.model.UserModel;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.infraestructure.entity.UserEntity;
import com.kevin.emazon_users.infraestructure.mapper.IUserEntityMapper;
import com.kevin.emazon_users.infraestructure.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class UserJpaAdapter implements IUserPersistentPort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public void saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.userToUserEntity(userModel);
        userEntity.setRoleEnum(userModel.getRole());
        userRepository.save(userEntity);
    }

    @Override
    public boolean exist(String identificationNumber, String email) {
        return userRepository.existsByIdentificationNumberOrEmail(identificationNumber, email);
    }

    @Override
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByEmail(username).map(userEntityMapper::userEntityToUser);
    }

}
