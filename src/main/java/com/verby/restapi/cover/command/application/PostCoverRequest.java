package com.verby.restapi.cover.command.application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCoverRequest {

    private long contestId;
    private long userId;
    private String title;
    private MultipartFile video;
    private MultipartFile highlight;
    private MultipartFile image;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public void setHighlight(MultipartFile highlight) {
        this.highlight = highlight;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public PostCoverRequest(long contestId, String title) {
        this.contestId = contestId;
        this.title = title;
    }
}
