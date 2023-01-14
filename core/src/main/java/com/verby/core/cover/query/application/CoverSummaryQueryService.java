package com.verby.core.cover.query.application;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dao.CoverDetailQueryDao;
import com.verby.core.cover.query.dao.CoverQueryDao;
import com.verby.core.cover.query.dto.CoverDetailQueryModel;
import com.verby.core.cover.query.dto.CoverQueryModel;
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
        CoverQueryModel cover = coverQueryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));
        return coverQueryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));
    }

}
