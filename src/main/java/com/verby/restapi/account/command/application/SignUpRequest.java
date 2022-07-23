package com.verby.restapi.account.command.application;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String loginId;
    private String password;
    private String name;
    private String phone;
    private Boolean allowToMarketingNotification;

}
