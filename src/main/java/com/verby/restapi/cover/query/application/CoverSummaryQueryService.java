package com.verby.restapi.cover.query.application;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.cover.query.dao.CoverDetailQueryDao;
import com.verby.restapi.cover.query.dao.CoverQueryDao;
import com.verby.restapi.cover.query.dto.CoverDetailQueryModel;
import com.verby.restapi.cover.query.dto.CoverQueryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoverSummaryQueryService {

    private final CoverDetailQueryDao coverDetailQueryDao;
    private final CoverQueryDao coverQueryDao;

    public List<CoverDetailQueryModel> findAll(CoverSearchRequest request) {
        return coverDetailQueryDao.findAll(request);
    }

    public CoverQueryModel findById(Long id) {
        return coverQueryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));
    }

}
