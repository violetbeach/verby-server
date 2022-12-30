package com.verby.apiserver.external.cover.infra.client;

import com.verby.apiserver.external.cover.ExternalCoverQueryModel;
import com.verby.apiserver.external.cover.ExternalCoverSummaryService;
import com.verby.apiserver.external.cover.infra.dto.CoverSummary;
import com.verby.apiserver.external.cover.infra.mapper.CoverSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverSummaryServiceImpl implements ExternalCoverSummaryService {

    private final CoverSummaryDao coverSummaryDao;
    private final CoverSummaryMapper mapper;

    @Override
    public ExternalCoverQueryModel getQueryModel(long id) {
        CoverSummary coverSummary = coverSummaryDao.findById(id);
        return mapper.toQueryModel(coverSummary);
    }

}
