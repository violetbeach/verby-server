package com.verby.restapi.account.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByLoginId() {
        // given
        Account newAccount = new Account("violetBeach13", "password13", "honey", "01012345678", AccountStatus.ACTIVE, null, false);
        em.persistAndFlush(newAccount);

        // when
        Optional<Account> account = accountRepository.findByLoginId(newAccount.getLoginId());

        // then
        assertThat(account).isPresent();
    }

    @Test
    void save() {
        // given
        String loginId = "violetbeach13";
        Account account = new Account(loginId,
                "password1234",
                "nickname12",
                "01012345678",
                AccountStatus.ACTIVE,
                null,
                false
                );

        // when
        accountRepository.save(account);
        em.flush();

        // then
        Account result = em.find(Account.class, account.getId());
        assertThat(result).isNotNull();
    }
}