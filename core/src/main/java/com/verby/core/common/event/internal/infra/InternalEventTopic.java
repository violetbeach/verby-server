package com.verby.core.common.event.internal.infra;

public enum InternalEventTopic {

    COVER_EVENT("cover_event");

    private final String name;

    InternalEventTopic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
