package com.verby.core.cover.command.domain;

import org.springframework.data.repository.Repository;

public interface CoverEventRepository extends Repository<InternalCoverEvent, Long> {

    void save(InternalCoverEvent event);

}
