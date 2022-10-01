package com.verby.restapi.user.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginId {

    private final Long id;
    private final String loginId;

}
