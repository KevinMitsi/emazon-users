package com.kevin.emazon_users.application.dto;

import com.kevin.emazon_users.application.util.ValidationErrorMsg;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotNull(message = ValidationErrorMsg.NN_NAME)
    @NotBlank(message = ValidationErrorMsg.NB_NAME)
    private String name;

    @NotNull(message = ValidationErrorMsg.NN_SURNAME)
    @NotBlank(message = ValidationErrorMsg.NB_SURNAME)
    private String surname;

    @NotNull(message = ValidationErrorMsg.NN_IDN)
    @NotBlank(message = ValidationErrorMsg.NB_IDN)
    @Pattern(regexp = ValidationErrorMsg.VALID_IDN_PATTERN, message = ValidationErrorMsg.IV_PAT)
    private String identificationNumber;

    @NotNull(message = ValidationErrorMsg.NN_MBN)
    @NotBlank(message = ValidationErrorMsg.NB_MBN)
    @Pattern(regexp = ValidationErrorMsg.VALID_NUMBER_PATTERN, message = ValidationErrorMsg.BP_MBN)
    @Size(max = 13, min = 3, message = ValidationErrorMsg.BS_MBN)
    private String mobileNumber;

    @NotNull(message = ValidationErrorMsg.NN_DT)
    @NotBlank(message = ValidationErrorMsg.NB_DT)
    private String date;

    @NotNull(message = ValidationErrorMsg.NN_E)
    @NotBlank(message = ValidationErrorMsg.NB_E)
    @Email(message = ValidationErrorMsg.INVALID_EMAIL)
    private String email;

    @NotNull(message = ValidationErrorMsg.NN_PWD)
    @NotBlank(message = ValidationErrorMsg.NB_PWD)
    private String password;

    @NotNull(message = ValidationErrorMsg.NN_ROLE)
    private Long role;
}


