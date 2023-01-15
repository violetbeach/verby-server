package com.verby.internalconsumerserver.cover;

import org.springframework.data.repository.Repository;

public interface CoverQueryModelRepository extends Repository<CoverQueryModel, Long> {

    void save(CoverQueryModel model);

}
