package com.kevin.emazon_users.domain.usecase;

import com.kevin.emazon_users.domain.api.IUserServicePort;
import com.kevin.emazon_users.domain.model.User;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import com.kevin.emazon_users.infraestructure.exception.AlreadyCreatedUserException;
import com.kevin.emazon_users.infraestructure.exception.IlegalAgeException;

import java.time.LocalDate;
import java.time.Period;


public class UserUseCase implements IUserServicePort {
    private final IUserPersistentPort userPersistentPort;
    private final IEncryptPort encryptPort;

    public UserUseCase(IUserPersistentPort userPersistentPort, IEncryptPort encryptPort) {
        this.userPersistentPort = userPersistentPort;
        this.encryptPort = encryptPort;
    }

    @Override
    public void saveUser(User user) {
        verifyAndPrepareUser(user);
        userPersistentPort.saveUser(user);
    }

    private void verifyAndPrepareUser(User user) {
        if (userPersistentPort.exist(user.getIdentificationNumber(), user.getEmail())){
            throw new AlreadyCreatedUserException("Este usuario ya se encuentra creado");
        }
        if (!verifyAge(user)){
            throw new IlegalAgeException("El usuario debe ser mayor de edad para poderse crear");
        }
        encryptPort.encode(user.getPassword());
    }

    private boolean verifyAge(User user) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(user.getDate(), today);
        return period.getYears() >= 18;
    }
}
