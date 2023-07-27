package com.sekawanmedia.soaltes.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.sekawanmedia.soaltes.validator.EmailUniqueConstraint;
import com.sekawanmedia.soaltes.validator.PasswordEqualConstraint;
import com.sekawanmedia.soaltes.validator.UsernameUniqueConstraint;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@PasswordEqualConstraint
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegisterRequestDTO {

    @NotBlank
    @Email
    @EmailUniqueConstraint
    private String email;

    @NotBlank
    @UsernameUniqueConstraint
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;

}