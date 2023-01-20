package com.verby.apiserver.support.repository.cover;

import com.verby.core.cover.query.dto.CoverQueryModel;
import org.springframework.data.repository.Repository;

public interface TestCoverQueryModelRepository extends Repository<CoverQueryModel, Long> {

    void save(CoverQueryModel model);

}