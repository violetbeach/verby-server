package com.verby.apiserver.cover.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CoverResourcesPath {

    private final String video;
    private final String highlight;
    private final String image;

}
