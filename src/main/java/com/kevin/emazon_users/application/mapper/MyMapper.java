package com.kevin.emazon_users.application.mapper;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.util.ConstantUtilClassApplication;
import com.kevin.emazon_users.domain.model.RoleEnum;
import com.kevin.emazon_users.domain.model.UserModel;

public class MyMapper {
    private MyMapper() {}

    public static UserModel toUser(UserDto userDto) {
        UserModel userModel = new UserModel();
        userModel.setName(userDto.getName());
        userModel.setSurname(userDto.getSurname());
        userModel.setDate(ConstantUtilClassApplication.convertStringIntoDate(userDto.getDate()));
        userModel.setEmail(userDto.getEmail());
        userModel.setMobileNumber(userDto.getMobileNumber());
        userModel.setIdentificationNumber(userDto.getIdentificationNumber());
        userModel.setPassword(userDto.getPassword());
        userModel.setRole(RoleEnum.fromId(userDto.getRole()));
        return userModel;
    }
}
