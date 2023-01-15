package com.verby.internalconsumerserver.support.repository;

import com.verby.internalconsumerserver.cover.CoverQueryModel;
import org.springframework.data.repository.Repository;

public interface TestCoverQueryModelRepository extends Repository<CoverQueryModel, Long> {

    CoverQueryModel findById(Long id);

}
