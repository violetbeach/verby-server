package com.verby.apiserver.user.command.domain;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface UnavailableIDRepository extends Repository<UnavailableID, Long> {

    List<UnavailableID> findAll();

}
