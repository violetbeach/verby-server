package com.verby.core.covercomment.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentRequest {
    private String content;
    @JsonIgnore
    private long userId;
    @JsonIgnore
    private long coverId;

    public void setCoverId(long coverId) {
        this.coverId = coverId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public CommentRequest(String content) {
        this.content = content;
    }
}

