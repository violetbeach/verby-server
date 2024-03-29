package com.verby.internalconsumerserver.cover.infra.client;

import com.verby.internalconsumerserver.cover.CoverQueryModel;
import com.verby.internalconsumerserver.cover.CoverQueryModelService;
import com.verby.internalconsumerserver.cover.infra.dto.CoverSummary;
import com.verby.internalconsumerserver.cover.infra.mapper.CoverSummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverQueryModelServiceImpl implements CoverQueryModelService {

    private final CoverSummaryDao coverSummaryDao;
    private final CoverLikeDao coverLikeDao;
    private final CoverCommentDao coverCommentDao;
    private final CoverSummaryMapper mapper;

    @Override
    public CoverQueryModel getQueryModel(long id) {
        CoverSummary coverSummary = coverSummaryDao.findById(id);
        long likeCount = coverLikeDao.countByCoverId(id);
        long commentCount = coverCommentDao.countByCoverId(id);
        return mapper.toQueryModel(coverSummary, likeCount, commentCount);
    }

}
