package com.kevin.emazon_users.insfraestructure.adapters;
import com.kevin.emazon_users.domain.model.User;
import com.kevin.emazon_users.infraestructure.adapters.UserJpaAdapter;
import com.kevin.emazon_users.infraestructure.entity.UserEntity;
import com.kevin.emazon_users.infraestructure.repository.IUserRepository;
import com.kevin.emazon_users.infraestructure.mapper.IUserEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @Test
    void testSaveUser() {
        User user = new User(null,"John", "Doe", "123456789", "+1234567890", LocalDate.now().minusYears(20), "john.doe@example.com", "password", 1L);
        when(userEntityMapper.toUserEntity(user)).thenReturn(new UserEntity());

        userJpaAdapter.saveUser(user);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testExist() {
        String identificationNumber = "123456789";
        String email = "john.doe@example.com";
        when(userRepository.existsByIdentificationNumberOrEmail(identificationNumber, email)).thenReturn(true);

        boolean exists = userJpaAdapter.exist(identificationNumber, email);

        assertTrue(exists);
    }
}