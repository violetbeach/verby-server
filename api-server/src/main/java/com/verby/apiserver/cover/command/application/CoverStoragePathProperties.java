package com.verby.apiserver.cover.command.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "static.paths.cover")
@ConstructorBinding
public class CoverStoragePathProperties {

    private final String video;
    private final String highlight;
    private final String image;

}
