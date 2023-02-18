package com.verby.core.cover.query.dao;

import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dto.CoverQueryModel;

import java.util.List;

public interface CustomCoverQueryDao {

    List<CoverQueryModel> findAll(CoverSearchRequest coverSearchRequest);

}
