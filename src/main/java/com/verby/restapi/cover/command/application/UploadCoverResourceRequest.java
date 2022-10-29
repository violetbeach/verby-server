package com.verby.restapi.cover.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UploadCoverResourceRequest {

    private final MultipartFile video;
    private final MultipartFile highlight;
    private final MultipartFile image;

}
