package com.verby.restapi.account.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    @DisplayName("Account 생성자 테스트")
    public void Account() {
        // given
        String loginId = "violetBeach12";
        String password = "password12";

        // when
        Account account = new Account(loginId, password, "honey", "01012345678", AccountStatus.ACTIVE, null, false);

        // then
        assertThat(account).isNotNull();
        assertEquals(loginId, account.getLoginId());
        assertEquals(password, account.getPassword());
    }

    @Test
    @DisplayName("비밀번호 변경 테스트")
    public void resetPassword() {
        // given
        String newPassword = "changedPassword";
        Account account = new Account("loginId", "password", "honey", "01012345678", AccountStatus.ACTIVE, null, false);

        // when
        account.resetPassword(newPassword);

        // then
        assertThat(account).isNotNull();
        assertEquals(newPassword, account.getPassword());
    }

}