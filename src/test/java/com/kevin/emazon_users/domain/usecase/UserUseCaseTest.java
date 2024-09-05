package com.kevin.emazon_users.domain.usecase;

import com.kevin.emazon_users.domain.model.RoleEnum;
import com.kevin.emazon_users.domain.model.UserModel;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import com.kevin.emazon_users.infraestructure.exception.AlreadyCreatedUserException;
import com.kevin.emazon_users.infraestructure.exception.IlegalAgeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistentPort userPersistentPort;

    @Mock
    private IEncryptPort encryptPort;



    @Test
    void testSaveUser() {
        UserModel userModel = new UserModel(null,"John", "Doe", "123456789", "+1234567890", new Date(), "john.doe@example.com", "password", RoleEnum.fromId(1L));
        when(userPersistentPort.exist(userModel.getIdentificationNumber(), userModel.getEmail())).thenReturn(false);

        userUseCase.saveUser(userModel);

        verify(userPersistentPort, times(1)).saveUser(userModel);
        verify(encryptPort, times(1)).encode(userModel.getPassword());
    }

    @Test
    void testSaveUserAlreadyExists() {
        UserModel userModel = new UserModel(null,"John", "Doe", "123456789", "+1234567890",new Date(), "john.doe@example.com", "password", RoleEnum.fromId(1L));
        when(userPersistentPort.exist(userModel.getIdentificationNumber(), userModel.getEmail())).thenReturn(true);

        assertThrows(AlreadyCreatedUserException.class, () -> userUseCase.saveUser(userModel));
    }

    @Test
    void testSaveUserUnderage() {
        UserModel userModel = new UserModel(null,"John", "Doe", "123456789", "+1234567890", new Date(), "john.doe@example.com", "password", RoleEnum.fromId(1L));
        when(userPersistentPort.exist(userModel.getIdentificationNumber(), userModel.getEmail())).thenReturn(false);

        assertThrows(IlegalAgeException.class, () -> userUseCase.saveUser(userModel));
    }
}