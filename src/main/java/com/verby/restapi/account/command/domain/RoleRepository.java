package com.verby.restapi.account.command.domain;

import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<AccountRole, Long> {
    AccountRole findByName(Role role);
}
