package com.verby.internalconsumerserver.cover.infra.client;

import com.verby.internalconsumerserver.cover.infra.dto.CoverLike;
import org.springframework.data.repository.Repository;

public interface CoverLikeSummaryDao extends Repository<CoverLike, Long> {

    long countById(long coverId);

}
