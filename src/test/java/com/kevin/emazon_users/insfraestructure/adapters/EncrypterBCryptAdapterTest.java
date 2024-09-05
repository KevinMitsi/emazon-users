package com.kevin.emazon_users.insfraestructure.adapters;
import com.kevin.emazon_users.infraestructure.adapters.EncrypterBCryptAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EncrypterBCryptAdapterTest {

    @InjectMocks
    private EncrypterBCryptAdapter encripterBCryptAdapter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testEncode() {
        String rawPassword = "password";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        String result = encripterBCryptAdapter.encode(rawPassword);

        assertEquals(encodedPassword, result);
    }
}