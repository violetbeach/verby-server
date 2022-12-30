package com.verby.apiserver.cover.query.application;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.EntityNotFoundException;
import com.verby.apiserver.cover.command.application.CoverSearchRequest;
import com.verby.apiserver.cover.query.dao.CoverDetailQueryDao;
import com.verby.apiserver.cover.query.dao.CoverQueryDao;
import com.verby.apiserver.cover.query.dto.CoverDetailQueryModel;
import com.verby.apiserver.cover.query.dto.CoverQueryModel;
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
