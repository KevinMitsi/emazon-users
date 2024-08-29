package com.kevin.emazon_users.application.handler.impl;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.IUserHandler;
import com.kevin.emazon_users.application.mapper.IUserDtoMapper;
import com.kevin.emazon_users.domain.api.IUserServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IUserDtoMapper userDtoMapper;
    @Override
    public void saveUser(UserDto userDto) {
        userServicePort.saveUser(userDtoMapper.toUser(userDto));
    }
}
