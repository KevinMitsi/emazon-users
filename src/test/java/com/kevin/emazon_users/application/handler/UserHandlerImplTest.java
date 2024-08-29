package com.kevin.emazon_users.application.handler;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.application.handler.impl.UserHandlerImpl;
import com.kevin.emazon_users.application.mapper.IUserDtoMapper;
import com.kevin.emazon_users.domain.api.IUserServicePort;
import com.kevin.emazon_users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserHandlerImplTest { @InjectMocks
private UserHandlerImpl userHandlerImpl;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserDtoMapper userDtoMapper;

    @Test
    void testSaveUser() {
        UserDto userDto = new UserDto("John", "Doe", "123456789", "+1234567890", LocalDate.now(), "john.doe@example.com", "password", 1L);
        when(userDtoMapper.toUser(userDto)).thenReturn(new User()); // Suponiendo que hay un constructor vacío en User

        userHandlerImpl.saveUser(userDto);

        verify(userServicePort, times(1)).saveUser(any());
    }

}
