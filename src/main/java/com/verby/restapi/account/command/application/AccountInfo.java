package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountInfo {
    private Long id;
    private String loginId;

    static AccountInfo from(Account account) {
        return new AccountInfo(account.getId(), account.getLoginId());
    }
}
