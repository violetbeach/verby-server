package com.verby.apiserver.support.fixture.domain;

import com.verby.core.user.command.domain.Gender;
import com.verby.core.user.command.domain.User;
import com.verby.core.user.command.domain.UserRole;

import java.util.Set;

public enum UserFixture {

    NORMAL_USER("member1234", "password1234!", "홍길동", "01050339203", Gender.MALE)
    ;

    private final String loginId;
    private final String password;
    private final String name;
    private final String phone;
    private final Gender gender;

    UserFixture(String loginId, String password, String name, String phone, Gender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public User getUser() {
        return new User(loginId, password, name, phone, gender, null, false);
    }

    public User getUser(String loginId) {
        return new User(loginId, password, name, phone, gender, null, false);
    }

    public User getUser(String loginId, String phone) {
        return new User(loginId, password, name, phone, gender, null, false);
    }

    public User getUser(String loginId, String password, Set<UserRole> roles) {
        return new User(loginId, password, name, phone, gender, roles, false);
    }

}
