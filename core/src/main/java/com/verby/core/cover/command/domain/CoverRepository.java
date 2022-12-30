package com.verby.core.cover.command.domain;

import com.verby.core.cover.Cover;
import org.springframework.data.repository.Repository;

public interface CoverRepository extends Repository<Cover, Long> {

    Cover save(Cover cover);

}
