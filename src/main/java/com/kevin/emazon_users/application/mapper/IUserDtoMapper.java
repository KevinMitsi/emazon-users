package com.kevin.emazon_users.application.mapper;

import com.kevin.emazon_users.application.dto.UserDto;
import com.kevin.emazon_users.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserDtoMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
