package com.verby.core.cover.command.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverRepository extends Repository<Cover, Long> {

    Cover save(Cover cover);
    Optional<Cover> findById(Long id);

}
