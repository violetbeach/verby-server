package com.verby.restapi.external.cover.infra.client;

import com.verby.restapi.external.cover.infra.dto.CoverSummary;
import org.springframework.data.repository.Repository;

public interface CoverSummaryDao extends Repository<CoverSummary, Long> {

    CoverSummary findById(long id);
    CoverSummary save(CoverSummary cover);

}
