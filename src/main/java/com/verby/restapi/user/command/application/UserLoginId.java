package com.verby.restapi.user.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginId {

    private final long id;
    private final String loginId;

}
