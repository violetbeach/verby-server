package com.verby.core.cover.command.application;

import com.verby.core.cover.command.domain.Cover;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostedCoverInfo {

    private final Long id;
    private final long contestId;
    private final String title;
    private final String content;
    private final String video;
    private final String highlight;
    private final String image;
    private final LocalDateTime createdAt;

    public static PostedCoverInfo from(Cover cover) {
        return new PostedCoverInfo(
                cover.getId(),
                cover.getContestId(),
                cover.getTitle(),
                cover.getContent(),
                cover.getVideo(),
                cover.getHighlight(),
                cover.getImage(),
                cover.getCreatedAt()
        );
    }

}
