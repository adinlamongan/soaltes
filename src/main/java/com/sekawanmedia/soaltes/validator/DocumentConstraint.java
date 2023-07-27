package com.sekawanmedia.soaltes.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = DocumentConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentConstraint {

    String message() default "invalid type document";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
