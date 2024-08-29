package com.kevin.emazon_users.infraestructure.config.beans;

import com.kevin.emazon_users.domain.api.IUserServicePort;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import com.kevin.emazon_users.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeansClass {
    @Bean
    IUserServicePort userServicePort(IUserPersistentPort userPersistentPort, IEncryptPort encryptPort){
        return new UserUseCase(userPersistentPort, encryptPort);
    }


}
