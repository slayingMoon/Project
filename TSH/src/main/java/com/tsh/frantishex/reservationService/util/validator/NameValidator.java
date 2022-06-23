package com.tsh.frantishex.reservationService.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name,String> {


	@Override
	public void initialize(Name constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String match="\\b([A-Z][-,a-z. ']*[ ]*)+";
		if(value==null)return true;
		Pattern p = Pattern.compile(match);
		Matcher m = p.matcher(value);
		return m.matches();

	}
}
