package com.verby.internalconsumerserver.cover.infra.client;

import com.verby.internalconsumerserver.cover.infra.dto.CoverSummary;
import org.springframework.data.repository.Repository;

public interface CoverSummaryDao extends Repository<CoverSummary, Long> {

    CoverSummary findById(long id);

}
