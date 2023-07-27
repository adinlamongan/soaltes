package com.sekawanmedia.soaltes.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = ImageConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageConstraint {

    String message() default "invalid type image";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
