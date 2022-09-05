package com.verby.restapi.account.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class VerificationTokenTest {

    @Test
    @DisplayName("비밀번호 변경에 대한 토큰은 7일간 유효하다.")
    void setExpirationDate() {
        // given
        Account account = new Account("violetBeach13", "password13", "honey", "01012345678", AccountStatus.ACTIVE, null, false);

        // when
        VerificationToken verificationToken = new VerificationToken(VerificationType.SET_PASSWORD, account);

        // given
        long difference = LocalDate.now().until(verificationToken.getExpirationDate().toLocalDate(), ChronoUnit.DAYS);
        assertEquals(7, difference);
    }

}