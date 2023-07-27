package com.sekawanmedia.soaltes.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sekawanmedia.soaltes.dto.RegisterRequestDTO;


public class PasswordEqualConstraintValidator implements ConstraintValidator<PasswordEqualConstraint, Object> {

    @Override
    public boolean isValid(Object data, ConstraintValidatorContext context) {
        RegisterRequestDTO registerRequestDTO = (RegisterRequestDTO) data;
        return registerRequestDTO.getConfirmPassword().equals(registerRequestDTO.getPassword());
    }
    
}
