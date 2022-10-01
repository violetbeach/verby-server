package com.verby.restapi.user.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {

    Optional<User> findById(long id);
    Optional<User> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
    boolean existsByPhone(String phone);
    User save(User user);

}
