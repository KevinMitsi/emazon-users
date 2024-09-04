package com.kevin.emazon_users.application.handler.impl;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.IUserHandler;
import com.kevin.emazon_users.application.util.ConstantUtilClassApplication;
import com.kevin.emazon_users.domain.api.IUserServicePort;
import com.kevin.emazon_users.domain.model.RoleEnum;
import com.kevin.emazon_users.domain.model.UserModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kevin.emazon_users.application.mapper.MyMapper.toUser;

@Service
@AllArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    @Override
    public void saveUser(UserDto userDto) {
        userServicePort.saveUser(toUser(userDto));
    }


}
