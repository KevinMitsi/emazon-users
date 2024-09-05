package com.kevin.emazon_users.application.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.kevin.emazon_users.application.util.ValidationErrorMsg.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private static final int MAX_SIZE_MOBILE = 13;
    private static final int MIN_SIZE_MOBILE = 3;

    @NotNull(message = NN_NAME)
    @NotBlank(message = NB_NAME)
    private String name;

    @NotNull(message = NN_SURNAME)
    @NotBlank(message = NB_SURNAME)
    private String surname;

    @NotNull(message = NN_IDN)
    @NotBlank(message = NB_IDN)
    @Pattern(regexp = VALID_IDN_PATTERN, message = IV_PAT)
    private String identificationNumber;

    @NotNull(message = NN_MBN)
    @NotBlank(message = NB_MBN)
    @Pattern(regexp = VALID_NUMBER_PATTERN, message = BP_MBN)
    @Size(max = MAX_SIZE_MOBILE, min = MIN_SIZE_MOBILE, message = BS_MBN)
    private String mobileNumber;

    @NotNull(message = NN_DT)
    @NotBlank(message = NB_DT)
    private String date;

    @NotNull(message = NN_E)
    @NotBlank(message = NB_E)
    @Email(message = INVALID_EMAIL)
    private String email;

    @NotNull(message = NN_PWD)
    @NotBlank(message = NB_PWD)
    private String password;

    @NotNull(message = NN_ROLE)
    private Long role;
}


