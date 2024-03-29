package com.verby.core.cover.command.application;

import com.verby.core.common.event.Events;
import com.verby.core.cover.command.domain.Cover;
import com.verby.core.cover.command.domain.CoverEventRepository;
import com.verby.core.cover.command.domain.CoverUpdatedEvent;
import com.verby.core.cover.command.domain.InternalCoverEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class CoverCreatedEventHandler {

    private final CoverEventRepository coverEventRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(CoverUpdatedEvent event) {
        Cover cover = event.getCover();
        InternalCoverEvent coverEvent = new InternalCoverEvent(
                cover.getId(),
                CoverEventType.UPDATE,
                cover.toString());
        coverEventRepository.save(coverEvent);
        Events.raise(coverEvent);
    }

}
