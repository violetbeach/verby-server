package com.verby.restapi.support.fixture.domain;

import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.AccountStatus;

public enum AccountFixture {

    NORMAL_ACCOUNT("member1234", "nickname12", "violetbeach@github.com", "https://images")
    ;

    private final String loginId;
    private final String password;
    private final String name;
    private final String phone;

    AccountFixture(String loginId, String password, String name, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public Account getAccount() {
        return new Account(loginId, password, name, phone, AccountStatus.ACTIVE, null, false);
    }

    public Account getAccount(String loginId) {
        return new Account(loginId, password, name, phone, AccountStatus.ACTIVE, null, false);
    }

    public Account getAccount(String loginId, String phone) {
        return new Account(loginId, password, name, phone, AccountStatus.ACTIVE, null, false);
    }

}
