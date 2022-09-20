package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.AccountRepository;
import com.verby.restapi.account.command.domain.AccountStatus;
import com.verby.restapi.account.exception.LoginIdDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@DisplayName("AccountService의")
class AccountServiceTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @Nested
    @DisplayName("signup 메서드는")
    class signup {

        @Test
        @DisplayName("생성할 회원 데이터를 가지고 회원을 생성하고, 생성한 회원을 반환한다.")
        void success() {
            // given
            SignUpRequest request = new SignUpRequest("violetbeach12", "password1234", "Honey", null, "01043042900", true);

            // when
            AccountInfo accountInfo = accountService.signUp(request);

            // then
            assertThat(accountInfo).isNotNull();
        }

        @Test
        @DisplayName("생성할 로그인 ID가 이미 존재하면 LoginIdDuplicateException이 발생한다.")
        void fail_duplicateLoginId() {
            // given
            String duplicatedLoginId = "violetbeach13";
            Account account = new Account(duplicatedLoginId,
                    "password1234",
                    "nickname12",
                    "01012345678",
                    AccountStatus.ACTIVE,
                    null,
                    false
            );
            accountRepository.save(account);

            SignUpRequest request = new SignUpRequest(duplicatedLoginId, "password1234", "Honey", null, "01043042900", true);

            // when & then
            assertThatThrownBy(() -> accountService.signUp(request))
                    .isInstanceOf(LoginIdDuplicateException.class);
        }

    }

}