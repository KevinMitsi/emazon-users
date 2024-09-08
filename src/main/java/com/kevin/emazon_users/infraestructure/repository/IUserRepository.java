package com.kevin.emazon_users.infraestructure.repository;

import com.kevin.emazon_users.infraestructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByIdentificationNumberOrEmail(String identificationNumber, String email);
    Optional<UserEntity> findByEmail(String email);
}
