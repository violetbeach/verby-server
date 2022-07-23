package com.verby.restapi.account.command.application;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SignUpRequest {

    private final String loginId;
    private final String password;
    private final String name;
    private final LocalDateTime birthday;
    private final String phone;
    private final Boolean allowToMarketingNotification;

}
