package com.verby.core.cover.infra;

import com.verby.core.common.event.internal.InternalEventPublisher;
import com.verby.core.cover.command.domain.CoverEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Transactional
@Service
@RequiredArgsConstructor
public class CoverInternalEventHandler {

    private final InternalEventPublisher publisher;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(CoverEvent event) {
        event.publish();
        publisher.publish(ChannelTopic.of("cover_event"), event.getCoverId());
    }

}
