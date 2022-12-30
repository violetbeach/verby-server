package com.verby.apiserver.support.fixture.domain;

import com.verby.apiserver.inquiry.command.domain.Inquiry;

public enum InquiryFixture {

    NORMAL_INQUIRY("노래가 재생되지 않습니다.", "노래가 재생되지 않는데 어떻게 하죠?")
    ;

    private final String title;
    private final String content;

    InquiryFixture(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Inquiry getInquiry(long inquirerId) {
        return new Inquiry(inquirerId, title, content);
    }

}
