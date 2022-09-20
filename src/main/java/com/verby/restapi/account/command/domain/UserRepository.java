package com.verby.restapi.account.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);
    Optional<User> findById(long id);
}
