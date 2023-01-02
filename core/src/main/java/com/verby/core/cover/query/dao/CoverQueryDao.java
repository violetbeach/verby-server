package com.verby.core.cover.query.dao;

import com.verby.core.cover.query.dto.CoverQueryModel;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CoverQueryDao extends Repository<CoverQueryModel, Long> {

    Optional<CoverQueryModel> findById(long id);

}
