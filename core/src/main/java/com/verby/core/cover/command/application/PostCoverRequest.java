package com.verby.core.cover.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCoverRequest {

    private long contestId;
    @NotEmpty
    private String title;
    private String content;
    private String video;
    private String highlight;
    private String image;
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private String requestedBy;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public PostCoverRequest(long contestId, String title, String content, String video, String highlight, String image) {
        this.contestId = contestId;
        this.title = title;
        this.content = content;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
    }
}
