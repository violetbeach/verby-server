package com.verby.core.user.command.application;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordComplexityValidator implements ConstraintValidator<Complexity, Object> {

    private int min;
    private final String engReg = "[a-zA-Z]";
    private final String numReg = "[0-9]";
    private final String specReg = "[^0-9a-zA-Z]";

    @Override
    public void initialize(Complexity passwordComplexity) {
        this.min = passwordComplexity.min();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        String password = object.toString();

        int complexity = 0;
        complexity += Pattern.compile(engReg).matcher(password).find() ? 1:0;
        complexity += Pattern.compile(numReg).matcher(password).find() ? 1:0;
        complexity += Pattern.compile(specReg).matcher(password).find() ? 1:0;

        return complexity >= min;
    }

}
