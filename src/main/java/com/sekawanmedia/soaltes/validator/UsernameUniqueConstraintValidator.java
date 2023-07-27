package com.sekawanmedia.soaltes.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sekawanmedia.soaltes.repository.AppUserRepo;


public class UsernameUniqueConstraintValidator implements ConstraintValidator<UsernameUniqueConstraint, String> {

    @Autowired
    private AppUserRepo appUserRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !appUserRepo.existsByUsername(value);
    }
    
}
