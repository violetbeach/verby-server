package com.verby.core.common.event.internal;

import com.verby.core.common.event.internal.infra.InternalEventTopic;

public interface InternalEventPublisher {

    public void publish(InternalEventTopic topic, Object message);

}
