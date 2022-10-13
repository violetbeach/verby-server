package com.verby.restapi.user.command.application;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SignUpRequestTest {
    
    static Validator validator;
    
    @BeforeAll
    static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @ParameterizedTest
    @MethodSource("paramsForPasswordRegex")
    void password_regex(String password, boolean expected) {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test123")
                .password(password)
                .name("TestName")
                .birthday(LocalDate.of(1994, 2, 10))
                .phone("01010392049")
                .allowToMarketingNotification(true)
                .token("token")
                .build();

        // when
        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(signUpRequest);

        // then
        assertThat(violations.isEmpty()).isEqualTo(expected);
    }

    static Stream<Arguments> paramsForPasswordRegex() {
        return Stream.of(
                Arguments.of("ab12!@", false),
                Arguments.of("abcdefghijk", false),
                Arguments.of("01223456789", false),
                Arguments.of("!@#$%^&*()", false),
                Arguments.of("password1234", true),
                Arguments.of("password!@#$", true),
                Arguments.of("12345678!@#$", true),
                Arguments.of("abcd1234!@#$", true)
        );
    }

}