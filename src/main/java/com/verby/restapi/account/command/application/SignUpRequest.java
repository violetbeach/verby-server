package com.verby.restapi.account.command.application;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Builder
public class SignUpRequest {

    @NotNull
    @Size(min = 6, max = 12)
    @Pattern(regexp = "^[a-z_\\d]*$", message = "영문 소문자 또는 숫자 또는 특수기호(_)만 사용 가능합니다")
    private final String loginId;
    @NotNull @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "영문과 숫자를 조합해서 사용해야합니다")
    private final String password;
    @NotBlank
    private final String name;
    private final LocalDateTime birthday;
    @NotBlank @Pattern(regexp = "^\\d*$", message = "숫자만 사용할 수 있습니다")
    private final String phone;
    @NotNull
    private final Boolean allowToMarketingNotification;

}
