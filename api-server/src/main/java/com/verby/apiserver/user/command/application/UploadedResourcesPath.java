package com.verby.apiserver.user.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadedResourcesPath {

    private final String video;
    private final String highlight;
    private final String image;

}
