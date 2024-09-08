package com.kevin.emazon_users.domain.model;

import com.kevin.emazon_users.infraestructure.exception.NotFoundRoleException;
import lombok.Getter;


import java.util.Objects;


@Getter
public enum RoleEnum {
    ROLE_ADMINISTRADOR(1L, "ROLE_ADMINISTRADOR"),
    ROLE_AUX_BODEGA(2L, "ROLE_AUX_BODEGA"),
    ROLE_CLIENTE(3L, "ROLE_CLIENTE");

    public static final String NOT_FOUND_ROLE_BYNAME_MESSAGE = "No se encontró el rol con el nombre: ";
    public static final String NOT_FOUND_ROLE_BYID_MESSAGE = "No se encontró el rol con el ID: ";
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
        throw new NotFoundRoleException(NOT_FOUND_ROLE_BYNAME_MESSAGE + name);
    }

    // Método para obtener un Role por su ID
    public static RoleEnum fromId(Long id) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (Objects.equals(roleEnum.getId(), id)) {
                return roleEnum;
            }
        }
        throw new NotFoundRoleException(NOT_FOUND_ROLE_BYID_MESSAGE + id);
    }


}
