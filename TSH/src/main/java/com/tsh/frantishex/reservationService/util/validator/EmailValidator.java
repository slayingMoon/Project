package com.tsh.frantishex.reservationService.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator  implements ConstraintValidator<Email,String> {
    @Override
    public void initialize(Email constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String match="[a-zA-Z\\d]+@[a-z]+.[a-z]+";

        Pattern p = Pattern.compile(match);
        Matcher m = p.matcher(value);
        return m.matches();
    }
    ///
}
