package com.kevin.emazon_users.domain.usecase;

import com.kevin.emazon_users.domain.model.User;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import com.kevin.emazon_users.infraestructure.exception.AlreadyCreatedUserException;
import com.kevin.emazon_users.infraestructure.exception.IlegalAgeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

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
        User user = new User(null,"John", "Doe", "123456789", "+1234567890", LocalDate.now().minusYears(20), "john.doe@example.com", "password", 1L);
        when(userPersistentPort.exist(user.getIdentificationNumber(), user.getEmail())).thenReturn(false);

        userUseCase.saveUser(user);

        verify(userPersistentPort, times(1)).saveUser(user);
        verify(encryptPort, times(1)).encode(user.getPassword());
    }

    @Test
    void testSaveUserAlreadyExists() {
        User user = new User(null,"John", "Doe", "123456789", "+1234567890", LocalDate.now().minusYears(20), "john.doe@example.com", "password", 1L);
        when(userPersistentPort.exist(user.getIdentificationNumber(), user.getEmail())).thenReturn(true);

        assertThrows(AlreadyCreatedUserException.class, () -> userUseCase.saveUser(user));
    }

    @Test
    void testSaveUserUnderage() {
        User user = new User(null,"John", "Doe", "123456789", "+1234567890", LocalDate.now().minusYears(10), "john.doe@example.com", "password", 1L);
        when(userPersistentPort.exist(user.getIdentificationNumber(), user.getEmail())).thenReturn(false);

        assertThrows(IlegalAgeException.class, () -> userUseCase.saveUser(user));
    }
}