package com.verby.core.cover.command.domain;

import com.verby.core.cover.CoverHit;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CoverHitRepository extends Repository<CoverHit, Long> {
    void save(CoverHit coverHit);
    Optional<CoverHit> findByCoverId(Long coverId);
    List<CoverHit> findAll();
    void delete(CoverHit coverHit);
}
