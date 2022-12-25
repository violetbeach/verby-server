package verby.apiserver.cover.query.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.EntityNotFoundException;
import verby.apiserver.cover.command.application.CoverSearchRequest;
import verby.apiserver.cover.query.dao.CoverDetailQueryDao;
import verby.apiserver.cover.query.dao.CoverQueryDao;
import verby.apiserver.cover.query.dto.CoverDetailQueryModel;
import verby.apiserver.cover.query.dto.CoverQueryModel;

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
