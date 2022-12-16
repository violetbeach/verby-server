package com.verby.restapi.external.cover;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ExternalCoverQueryDao extends Repository<ExternalCoverQueryModel, Long> {

    void save(ExternalCoverQueryModel model);
    Optional<ExternalCoverQueryModel> findById(long id);

}
