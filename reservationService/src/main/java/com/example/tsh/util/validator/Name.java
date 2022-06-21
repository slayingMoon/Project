package com.example.tsh.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
 String message() default "{Name invalid}";

 Class<?>[] groups() default {};

Class<? extends Payload>[] payload() default {};
}
