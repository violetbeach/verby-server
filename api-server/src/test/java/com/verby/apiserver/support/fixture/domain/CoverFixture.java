package com.verby.apiserver.support.fixture.domain;

import com.verby.core.cover.Cover;

public enum CoverFixture {

    NORMAL_COVER("테스트 커버 영상", "영상 설명 내용", "/test/video/test.mp4", "/test/highlight/test.mp4", "/image/highlight/test.mp4")
    ;

    private final String title;
    private final String content;
    private final String video;
    private final String highlight;
    private final String image;

    CoverFixture(String title, String content, String video, String highlight, String image) {
        this.title = title;
        this.content = content;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
    }

    public Cover getCover(long contestId, long publisherId) {
        return new Cover(contestId, publisherId, title, content, video, highlight, image);
    }

    public Cover getCover(long contestId, long publisherId, String title) {
        return new Cover(contestId, publisherId, title, content, video, highlight, image);
    }

}
