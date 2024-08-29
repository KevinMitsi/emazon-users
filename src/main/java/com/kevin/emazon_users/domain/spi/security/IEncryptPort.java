package com.kevin.emazon_users.domain.spi.security;

public interface IEncryptPort {
    String encode(String raw);
}
