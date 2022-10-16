package com.verby.restapi.user.command.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SMSCertificationRequest {

    @PhoneValidation
    private String phone;
    private int certificationNumber;

}
