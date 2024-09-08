package com.kevin.emazon_users.infraestructure.mapper;


import com.kevin.emazon_users.domain.model.UserModel;
import com.kevin.emazon_users.infraestructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    UserModel userEntityToUser(UserEntity userEntity);

    UserEntity userToUserEntity(UserModel userModel);
}
