package com.kevin.emazon_users.infraestructure.adapters.security;

import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class EncripterBCryptAdapter implements IEncryptPort {
    private final PasswordEncoder passwordEncoder;
    @Override
    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }
}
