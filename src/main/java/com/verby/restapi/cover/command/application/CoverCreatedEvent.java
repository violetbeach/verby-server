package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.Cover;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoverCreatedEvent {

    private final Cover cover;

}
