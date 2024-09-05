package com.kevin.emazon_users.infraestructure.security.util;

import com.kevin.emazon_users.domain.model.RoleEnum;
import com.kevin.emazon_users.domain.model.UserModel;
import com.kevin.emazon_users.infraestructure.entity.UserEntity;

public class SelfMapper {
    private SelfMapper(){}
    public static UserModel toUserModel(UserEntity userEntity){
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setName(userEntity.getName());
        userModel.setSurname(userEntity.getSurname());
        userModel.setIdentificationNumber(userEntity.getIdentificationNumber());
        userModel.setMobileNumber(userEntity.getMobileNumber());
        userModel.setEmail(userEntity.getEmail());
        userModel.setPassword(userEntity.getPassword());
        userModel.setRole(RoleEnum.fromName(userEntity.getRoleEnum().getName()));

        return userModel;
    }
}
