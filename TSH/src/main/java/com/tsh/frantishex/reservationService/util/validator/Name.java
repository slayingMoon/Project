package com.tsh.frantishex.reservationService.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = NameValidator.class)
public @interface Name {
 String message() default "{Name is not valid}";

 Class<?>[] groups() default {};

Class<? extends Payload>[] payload() default {};
}
