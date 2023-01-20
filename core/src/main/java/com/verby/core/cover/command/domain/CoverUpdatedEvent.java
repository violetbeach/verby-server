package com.verby.core.cover.command.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoverUpdatedEvent {

    private final Cover cover;

}
