package com.verby.core.user.command.application;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordComplexityValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Complexity {

    int min();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
