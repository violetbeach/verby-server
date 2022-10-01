package com.verby.restapi.user.command.domain;

import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<UserRole, Long> {
    UserRole findByName(Role role);
}
