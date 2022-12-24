package verby.apiserver.external.cover.infra.client;

import org.springframework.data.repository.Repository;
import verby.apiserver.external.cover.infra.dto.CoverSummary;

public interface CoverSummaryDao extends Repository<CoverSummary, Long> {

    CoverSummary findById(long id);
    CoverSummary save(CoverSummary cover);

}
