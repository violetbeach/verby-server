package com.verby.core.contest.query;

import org.springframework.data.repository.Repository;

public interface ContestDataDao extends Repository<ContestData, Long> {

    boolean existsById(Long id);

}
