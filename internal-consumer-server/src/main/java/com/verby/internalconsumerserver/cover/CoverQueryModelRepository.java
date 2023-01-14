package com.verby.internalconsumerserver.cover;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverQueryModelRepository extends Repository<CoverQueryModel, Long> {

    void save(CoverQueryModel model);
    Optional<CoverQueryModel> findById(long id);

}
