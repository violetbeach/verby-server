package com.verby.restapi.cover.query.dao;

import com.verby.restapi.cover.query.dto.CoverSummary;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverSummaryDao extends Repository<CoverSummary, Long> {
    Optional<CoverSummary> findById(long id);

}
