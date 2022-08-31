package com.verby.restapi.account.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void findByLoginId() {
        // given
        String loginId = "violetbeach13";
        jdbcTemplate.update("INSERT INTO account (login_id, password, name, status, allow_to_marketing_notification) VALUES" +
                "(?, '$2a$10$JtZ/Qb4VjL1KIvLMgNFKGOSWU.4LSFDpJZkqYOB4tM2A/wg1N/Vse', 'member', 'ACTIVE', false)", loginId);

        // when
        Optional<Account> account = accountRepository.findByLoginId(loginId);

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

        // then
        SqlRowSet rsAccount = jdbcTemplate.queryForRowSet(
                "select * from account where id = ?",
                account.getId());
        assertThat(rsAccount.next()).isTrue();
        assertThat(rsAccount.getString("login_id")).isEqualTo(loginId);
    }
}