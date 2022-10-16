package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.Gender;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Builder
public class SignUpRequest {

    @NotNull
    @Size(min = 6, max = 12)
    @Pattern(regexp = "^[a-z\\d]*$", message = "영문 소문자 또는 숫자만 사용 가능합니다.")
    private final String loginId;
    @NotNull @Size(min = 10, max = 16)
    @Complexity(min = 2, message = "영문과 숫자, 특수문자 중 2가지 이상을 조합해서 사용해야합니다.")
    private final String password;
    @NotBlank
    private final String name;
    private final LocalDate birthday;
    @PhoneValidation
    private final String phone;
    private final Gender gender;
    @NotNull
    private final Boolean allowToMarketingNotification;
    @NotBlank
    private final String token;

}
