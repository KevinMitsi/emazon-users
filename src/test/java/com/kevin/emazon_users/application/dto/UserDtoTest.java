package com.kevin.emazon_users.application.dto;

import com.kevin.emazon_users.application.util.ValidationErrorMsg;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testValidUserDto() {
        UserDto userDto = new UserDto(
                "John",
                "Doe",
                "123456789",
                "+123456789",
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertTrue(violations.isEmpty(), "El UserDto debería ser válido");
    }

    @Test
    void testNullName() {
        UserDto userDto = new UserDto(
                null, // Nombre nulo
                "Doe",
                "123456789",
                "+123456789",
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El nombre no debería ser nulo");
        assertEquals(ValidationErrorMsg.NN_NAME, violations.iterator().next().getMessage());
    }

    @Test
    void testBlankSurname() {
        UserDto userDto = new UserDto(
                "John",
                "   ", // Apellido vacío
                "123456789",
                "+123456789",
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El apellido no debería ser vacío");
        assertEquals(ValidationErrorMsg.NB_SURNAME, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidIdentificationNumber() {
        UserDto userDto = new UserDto(
                "John",
                "Doe",
                "ABC123", // Número de identificación no válido
                "+123456789",
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El número de identificación debe ser inválido");
        assertEquals(ValidationErrorMsg.IV_PAT, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidMobileNumber() {
        UserDto userDto = new UserDto(
                "John",
                "Doe",
                "123456789",
                "123456789", // Número de móvil sin '+'
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El número de móvil debería ser inválido");
        assertEquals(ValidationErrorMsg.BP_MBN, violations.iterator().next().getMessage());
    }

    @Test
    void testInvalidEmail() {
        UserDto userDto = new UserDto(
                "John",
                "Doe",
                "123456789",
                "+123456789",
                "01-01-2000",
                "invalid-email", // Email inválido
                "Password123",
                1L
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El email debería ser inválido");
        assertEquals(ValidationErrorMsg.INVALID_EMAIL, violations.iterator().next().getMessage());
    }

    @Test
    void testNullRole() {
        UserDto userDto = new UserDto(
                "John",
                "Doe",
                "123456789",
                "+123456789",
                "01-01-2000",
                "john.doe@example.com",
                "Password123",
                null // Rol nulo
        );

        Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
        assertFalse(violations.isEmpty(), "El rol no debería ser nulo");
        assertEquals(ValidationErrorMsg.NN_ROLE, violations.iterator().next().getMessage());
    }
}
