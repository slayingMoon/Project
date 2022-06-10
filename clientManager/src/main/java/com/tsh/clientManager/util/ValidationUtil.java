package com.tsh.clientManager.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtil {

    private final Validator validator;

    public ValidationUtil() {
        this.validator = Validation
                .buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E entity) {
        return validator.validate(entity).isEmpty();
    }
}
