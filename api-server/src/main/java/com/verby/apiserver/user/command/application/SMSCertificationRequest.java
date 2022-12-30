package com.verby.apiserver.user.command.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SMSCertificationRequest {

    @NotBlank
    @Size(min = 11, max = 13)
    @Pattern(regexp = "^\\d*$", message = "숫자만 사용할 수 있습니다")
    private String phone;
    private int certificationNumber;

}
