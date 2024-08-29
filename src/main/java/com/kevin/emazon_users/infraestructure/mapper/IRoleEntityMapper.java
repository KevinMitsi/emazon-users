package com.kevin.emazon_users.infraestructure.mapper;

import com.kevin.emazon_users.domain.model.Role;
import com.kevin.emazon_users.infraestructure.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    Role toRole(RoleEntity roleEntity);

}
