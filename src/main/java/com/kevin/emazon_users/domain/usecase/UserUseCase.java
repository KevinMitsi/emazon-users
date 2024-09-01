package com.kevin.emazon_users.domain.usecase;

import com.kevin.emazon_users.domain.api.IUserServicePort;
import com.kevin.emazon_users.domain.model.UserModel;
import com.kevin.emazon_users.domain.spi.IUserPersistentPort;
import com.kevin.emazon_users.domain.spi.security.IEncryptPort;
import com.kevin.emazon_users.domain.util.ConstantUtilClass;
import com.kevin.emazon_users.infraestructure.exception.AlreadyCreatedUserException;
import com.kevin.emazon_users.infraestructure.exception.IlegalAgeException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;



public class UserUseCase implements IUserServicePort {
    private final IUserPersistentPort userPersistentPort;
    private final IEncryptPort encryptPort;

    public UserUseCase(IUserPersistentPort userPersistentPort, IEncryptPort encryptPort) {
        this.userPersistentPort = userPersistentPort;
        this.encryptPort = encryptPort;
    }

    @Override
    public void saveUser(UserModel userModel) {
        verifyAndPrepareUser(userModel);
        userPersistentPort.saveUser(userModel);
    }

    private void verifyAndPrepareUser(UserModel userModel) {
        if (userPersistentPort.exist(userModel.getIdentificationNumber(), userModel.getEmail())){
            throw new AlreadyCreatedUserException("Este usuario ya se encuentra creado");
        }
        if (!verifyAge(userModel)){
            throw new IlegalAgeException("El usuario debe ser mayor de edad para poderse crear");
        }
        userModel.setPassword(encryptPort.encode(userModel.getPassword()));
    }

    private boolean verifyAge(UserModel userModel) {
        LocalDate birthDate = userModel.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        int age = Period.between(birthDate, today).getYears();

        return age >= ConstantUtilClass.LEGAL_AGE;
    }
}
