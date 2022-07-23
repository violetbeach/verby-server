package com.verby.restapi.account.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AccountRepository extends Repository<Account, Long> {
    Optional<Account> findByLoginId(String loginId);
    Account save(Account account);
}
