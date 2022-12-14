package com.verby.restapi.external.cover.infra.client;

import com.verby.restapi.external.cover.CoverQueryModel;
import com.verby.restapi.external.cover.CoverSummaryService;
import com.verby.restapi.external.cover.infra.dto.CoverSummary;
import com.verby.restapi.external.cover.infra.mapper.CoverSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverSummaryServiceImpl implements CoverSummaryService {

    private final CoverSummaryDao coverSummaryDao;
    private final CoverSummaryMapper mapper;

    @Override
    public CoverQueryModel getQueryModel(long id) {
        CoverSummary coverSummary = coverSummaryDao.findById(id);
        return mapper.toQueryModel(coverSummary);
    }

}
