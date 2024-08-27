package com.kevin.emazon_users.infraestructure.repository;

import com.kevin.emazon_users.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}
