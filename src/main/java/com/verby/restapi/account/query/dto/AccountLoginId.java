package com.verby.restapi.account.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountLoginId {

    private final Long id;
    private final String loginId;

}
