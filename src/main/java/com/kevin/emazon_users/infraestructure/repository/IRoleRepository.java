package com.kevin.emazon_users.infraestructure.repository;

import com.kevin.emazon_users.infraestructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

}
