package com.verby.restapi.cover.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCoverEntityRequest {

    private final long contestId;
    private final long userId;
    private final String title;
    private final String video;
    private final String highlight;
    private final String image;

}
