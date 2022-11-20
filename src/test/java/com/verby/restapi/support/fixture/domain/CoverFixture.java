package com.verby.restapi.support.fixture.domain;

import com.verby.restapi.cover.command.domain.Cover;

public enum CoverFixture {

    NORMAL_COVER("테스트 커버 영상", "/test/video/test.mp4", "/test/highlight/test.mp4", "/image/highlight/test.mp4")
    ;

    private final String title;
    private final String video;
    private final String highlight;
    private final String image;

    CoverFixture(String title, String video, String highlight, String image) {
        this.title = title;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
    }

    public Cover getCover(long contestId, long publisherId) {
        return new Cover(contestId, publisherId, title, video, highlight, image);
    }

}
