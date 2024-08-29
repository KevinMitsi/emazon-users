package com.kevin.emazon_users.domain.spi;

import com.kevin.emazon_users.domain.model.Role;

import java.util.Optional;

public interface IRolePersistentPort {
    Optional<Role> findById(Long id);
}
