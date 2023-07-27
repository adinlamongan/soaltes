package com.sekawanmedia.soaltes.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = VideoConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface VideoConstraint {

    String message() default "invalid type video";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
