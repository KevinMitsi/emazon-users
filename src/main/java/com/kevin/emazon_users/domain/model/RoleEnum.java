package com.kevin.emazon_users.domain.model;

import com.kevin.emazon_users.infraestructure.exception.NotFoundRoleException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public enum RoleEnum {
    ROLE_ADMINISTRADOR(1L, "ROLE_ADMINISTRADOR"),
    ROLE_AUX_BODEGA(2L, "ROLE_AUX_BODEGA"),
    ROLE_CLIENTE(3L, "ROLE_CLIENTE");

    private final Long id;
    private final String name;

    RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Método para obtener un Role por su nombre
    public static RoleEnum fromName(String name) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getName().equalsIgnoreCase(name)) {
                return roleEnum;
            }
        }
        throw new NotFoundRoleException("No se encontró el rol con el nombre: " + name);
    }

    // Método para obtener un Role por su ID
    public static RoleEnum fromId(Long id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (Objects.equals(roleEnum.getId(), id)) {
                return roleEnum;
            }
        }
        throw new NotFoundRoleException("No se encontró el rol con el ID: " + id);
    }

    public static List<RoleEnum> getAllRoles() {
        return Arrays.asList(RoleEnum.values());
    }

    public static List<Long> getAllIds() {
        return Arrays.stream(values())
                .map(RoleEnum::getId)
                .collect(Collectors.toList());
    }
}
