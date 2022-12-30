package com.verby.core.cover;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoverCreatedEvent {

    private final Cover cover;

}
