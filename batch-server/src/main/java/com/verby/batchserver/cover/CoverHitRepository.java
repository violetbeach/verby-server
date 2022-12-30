package com.verby.batchserver.cover;

import com.verby.core.cover.CoverHit;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CoverHitRepository extends Repository<CoverHit, Long> {
    List<CoverHit> findAll();
}