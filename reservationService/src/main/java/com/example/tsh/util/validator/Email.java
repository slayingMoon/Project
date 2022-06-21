package com.example.tsh.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = EmailValidator.class)
public @interface Email {
    String message() default "{Email is not valid.}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
