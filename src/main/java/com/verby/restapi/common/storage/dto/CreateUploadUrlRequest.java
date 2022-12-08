package com.verby.restapi.common.storage.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUploadUrlRequest {
    private Domain domain;
    private Resource resource;
}
