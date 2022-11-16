package com.verby.restapi.inquiry.command.application;

import com.verby.restapi.inquiry.command.domain.Inquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatedInquiryInfo {

    private final long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public static CreatedInquiryInfo from(Inquiry inquiry) {
        return new CreatedInquiryInfo(inquiry.getId(), inquiry.getTitle(), inquiry.getContent(), inquiry.getCreatedAt());
    }

}
