package com.verby.restapi.external.cover.infra.mapper;

import com.verby.restapi.external.cover.CoverQueryModel;
import com.verby.restapi.external.cover.infra.dto.CoverSummary;
import org.springframework.stereotype.Component;

@Component
public class CoverSummaryMapper {

    public CoverQueryModel toQueryModel(CoverSummary coverSummary) {
        return new CoverQueryModel(
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
