package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.Account;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountData {
    private Long id;
    private String loginId;

    static AccountData of(Account account) {
        return new AccountData(account.getId(), account.getLoginId());
    }
}
