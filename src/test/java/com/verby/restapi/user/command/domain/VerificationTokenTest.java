package com.verby.restapi.user.command.domain;

import com.verby.restapi.support.fixture.domain.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VerificationTokenTest {

    @Test
    @DisplayName("비밀번호 변경에 대한 토큰은 7일간 유효하다.")
    void setExpirationDate() {
        // given
        User user = UserFixture.NORMAL_USER.getUser();

        // when
        VerificationToken verificationToken = new VerificationToken(user.getPhone());

        // given
        long difference = LocalDate.now().until(verificationToken.getExpirationDate().toLocalDate(), ChronoUnit.DAYS);
        assertEquals(7, difference);
    }

}