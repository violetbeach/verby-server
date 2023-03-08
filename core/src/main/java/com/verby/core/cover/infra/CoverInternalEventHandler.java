package com.verby.core.cover.infra;

import com.verby.core.common.event.internal.InternalEventPublisher;
import com.verby.core.cover.command.domain.InternalCoverEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;

import static com.verby.core.common.event.internal.infra.InternalEventTopic.COVER_EVENT;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class CoverInternalEventHandler {
    private final InternalEventPublisher publisher;
    private final EntityManager em;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(InternalCoverEvent event) {
        publish(event);
        try {
            publisher.publish(COVER_EVENT, event.getCoverId());
        } catch (RuntimeException e) {
            log.warn("Internal Cover event publish failed {}.", e.getMessage());
        }
    }

    private void publish(InternalCoverEvent event) {
        em.merge(event).publish();
    }

}
