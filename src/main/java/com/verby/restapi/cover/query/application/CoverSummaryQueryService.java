package com.verby.restapi.cover.query.application;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.cover.query.dao.CoverSummaryQueryDao;
import com.verby.restapi.cover.query.dto.CoverSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.verby.restapi.config.database.RedisCacheKey.COVER_SUMMARY;

@Service
@RequiredArgsConstructor
public class CoverSummaryQueryService {

    private final CoverSummaryQueryDao coverSummaryQueryDao;

    public List<CoverSummary> findAll(CoverSearchRequest request) {
        return coverSummaryQueryDao.findAll(request);
    }
    
    public CoverSummary findById(Long id) {
        return coverSummaryQueryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));
    }

}
