package com.tsh.frantishex.reservationService.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {
    String message() default "{Phone is not valid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
