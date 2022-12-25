package verby.apiserver.external.cover.infra.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.external.cover.ExternalCoverQueryModel;
import verby.apiserver.external.cover.ExternalCoverSummaryService;
import verby.apiserver.external.cover.infra.dto.CoverSummary;
import verby.apiserver.external.cover.infra.mapper.CoverSummaryMapper;

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
