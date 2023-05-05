package com.verby.core.user.command.application;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordComplexityValidator implements ConstraintValidator<Complexity, Object> {

    private int min;
    private static final String ENG_REGEX = "[a-zA-Z]";
    private static final String NUM_REGEX = "\\d";
    private static final String SPEC_REGEX = "[^0-9a-zA-Z]";

    @Override
    public void initialize(Complexity passwordComplexity) {
        this.min = passwordComplexity.min();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        String password = object.toString();

        int complexity = 0;
        complexity += Pattern.compile(ENG_REGEX).matcher(password).find() ? 1:0;
        complexity += Pattern.compile(NUM_REGEX).matcher(password).find() ? 1:0;
        complexity += Pattern.compile(SPEC_REGEX).matcher(password).find() ? 1:0;

        return complexity >= min;
    }

}
