package com.verby.apiserver.inquiry.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InquiryRequest {

    @JsonIgnore
    private long userId;

    @NotBlank
    @Size(min = 6, max = 50)
    private String title;

    @NotBlank
    private String content;

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public InquiryRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
