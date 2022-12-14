package com.verby.restapi.external.cover;

import org.springframework.data.repository.Repository;

public interface CoverQueryModelWriteDao extends Repository<CoverQueryModel, Long> {

    void save(CoverQueryModel model);

}
