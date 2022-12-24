package verby.apiserver.external.cover.infra.mapper;

import org.springframework.stereotype.Component;
import verby.apiserver.external.cover.ExternalCoverQueryModel;
import verby.apiserver.external.cover.infra.dto.CoverSummary;

@Component
public class CoverSummaryMapper {

    public ExternalCoverQueryModel toQueryModel(CoverSummary coverSummary) {
        return new ExternalCoverQueryModel(
                coverSummary.getId(),
                coverSummary.getContestId(),
                coverSummary.getPublisherId(),
                coverSummary.getPublisherName(),
                coverSummary.getTitle(),
                coverSummary.getContent(),
                coverSummary.getVideo(),
                coverSummary.getHighlight(),
                coverSummary.getImage(),
                coverSummary.getArtistId(),
                coverSummary.getArtistName(),
                coverSummary.getSongId(),
                coverSummary.getSongName()
        );
    }

}
