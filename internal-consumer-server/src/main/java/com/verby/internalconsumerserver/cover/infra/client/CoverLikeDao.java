package com.verby.internalconsumerserver.cover.infra.client;

import com.verby.internalconsumerserver.cover.infra.dto.CoverLike;
import org.springframework.data.repository.Repository;

public interface CoverLikeDao extends Repository<CoverLike, Long> {

    long countByCoverId(long coverId);

}
