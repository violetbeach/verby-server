package com.verby.apiserver.external.cover.infra.mapper;

import com.verby.apiserver.external.cover.ExternalCoverQueryModel;
import com.verby.apiserver.external.cover.infra.dto.CoverSummary;
import org.springframework.stereotype.Component;

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
