package com.kevin.emazon_users.infraestructure.adapters;

import com.kevin.emazon_users.domain.model.Role;
import com.kevin.emazon_users.domain.spi.IRolePersistentPort;
import com.kevin.emazon_users.infraestructure.mapper.IRoleEntityMapper;
import com.kevin.emazon_users.infraestructure.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class RoleJpaAdapter implements IRolePersistentPort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id).map(roleEntityMapper::toRole);
    }
}
