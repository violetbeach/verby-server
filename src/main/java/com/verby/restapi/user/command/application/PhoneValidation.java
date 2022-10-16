package com.verby.restapi.user.command.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@NotBlank
@Size(min = 11, max = 13)
@Pattern(regexp = "^\\d*$", message = "숫자만 사용할 수 있습니다")
@interface PhoneValidation {}
