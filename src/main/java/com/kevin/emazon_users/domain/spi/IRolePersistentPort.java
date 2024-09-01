package com.kevin.emazon_users.domain.spi;

import com.kevin.emazon_users.domain.model.RoleEnum;


public interface IRolePersistentPort {
    RoleEnum findById(Long id);
}
