package com.verby.core.cover.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.verby.core.util.pagination.CursorRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoverSearchRequest {
    private Long contestId;

    @JsonIgnore
    private CursorRequest cursor;

    public CoverSearchRequest(Long contestId) {
        this.contestId = contestId;
    }

    public void setCursor(CursorRequest cursor) {
        this.cursor = cursor;
    }
}
