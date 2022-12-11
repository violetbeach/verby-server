package com.verby.restapi.cover.command.domain;

import org.springframework.data.repository.Repository;

public interface CoverEventRepository extends Repository<CoverEvent, Long> {

    void save(CoverEvent event);

}
