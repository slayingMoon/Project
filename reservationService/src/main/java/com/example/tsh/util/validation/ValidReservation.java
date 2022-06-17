package com.example.tsh.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Documented
@Constraint(validatedBy = ReservationValidator.class)
public @interface ValidReservation {
     String message() default "reservation not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
