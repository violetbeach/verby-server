package com.verby.restapi.cover.command.domain;

import org.springframework.data.repository.Repository;

public interface CoverRepository extends Repository<Cover, Long> {

    Cover save(Cover cover);

}
