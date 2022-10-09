package com.verby.restapi.support.fixture.domain;

import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.UserRole;

import java.util.Set;

public enum UserFixture {

    NORMAL_USER("member1234", "password1234!", "홍길동", "01050339203")
    ;

    private final String loginId;
    private final String password;
    private final String name;
    private final String phone;

    UserFixture(String loginId, String password, String name, String phone) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public User getUser() {
        return new User(loginId, password, name, phone, null, false);
    }

    public User getUser(String loginId) {
        return new User(loginId, password, name, phone, null, false);
    }

    public User getUser(String loginId, String phone) {
        return new User(loginId, password, name, phone, null, false);
    }

    public User getUser(String loginId, String password, Set<UserRole> roles) {
        return new User(loginId, password, name, phone, roles, false);
    }

}
