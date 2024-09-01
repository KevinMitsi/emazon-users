package com.kevin.emazon_users.application.util;

public class ValidationErrorMsg {
    private ValidationErrorMsg(){}
    public static final String NN_NAME = "NullNameException: El nombre no puede ser vacío";
    public static final String NB_NAME = "BlankNameException: El nombre no puede estar vacío ni lleno de espacios";


    public static final String NN_SURNAME = "NullSurnameException: El apellido no puede ser nulo";
    public static final String NB_SURNAME = "BlankSurnameException: El apellido no puede estar vacío ni lleno de espacios";


    public static final String VALID_IDN_PATTERN = "^\\d+$";
    public static final String NN_IDN = "NullIdentificationNumberException: El número de identificación no puede ser nulo";
    public static final String NB_IDN = "BlankIdentificationNumberException: El número de identificación no puede estar vacío ni lleno de espacios";
    public static final String IV_PAT = "InvalidPatterIdentificationNumber: El número de identificación solo puede contener números";



    public static final String VALID_NUMBER_PATTERN = "^\\+\\d+$";
    public static final String NN_MBN = "NullMobileNumberException: El número de teléfono no puede ser nulo";
    public static final String NB_MBN = "BlankMobileNumberException: El número de teléfono no puede estar vacío ni lleno de espacios";
    public static final String BP_MBN = "BadPatternMobileNumberException: El número de teléfono debe comenzar con '+' y contener solo números";
    public static final String BS_MBN = "InvalidMobileNumberSize: El número de teléfono debe tener máximo 13 digitos y almenos 3";


    public static final String NN_DT = "NullDateException: La fecha de nacimiento no puede ser nula";
    public static final String NB_DT = "BlankDateException: La fecha de nacimiento no puede estar vacía ni llean de espacios";


    public static final String NN_E = "NullEmailException: La dirección de correo electrónico no puede ser nula";
    public static final String NB_E = "BlankEmailException: La dirección de correo electrónico no puede estar vacía ni llena de espacios";
    public static final String INVALID_EMAIL = "InvalidEmailException: La dirección de correo electrónico no es válida utilice 'algo@algo.com'";


    public static final String NN_PWD = "NullPasswordException: La contraseña no puede ser nula";
    public static final String NB_PWD = "BlankPasswordException: La contrseña no puede ser vacía ni llena de espacios";


    public static final String NN_ROLE = "NullRoleException: El Rol de los usuarios no puede ser nulo";

}
