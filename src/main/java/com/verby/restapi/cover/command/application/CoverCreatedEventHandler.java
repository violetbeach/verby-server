package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.command.domain.CoverEvent;
import com.verby.restapi.cover.command.domain.CoverEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class CoverCreatedEventHandler {

    private final CoverEventRepository coverEventRepository;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handle(CoverCreatedEvent event) {
        Cover cover = event.getCover();
        CoverEvent coverEvent = new CoverEvent(
                cover.getId(),
                CoverEventType.CREATE,
                cover.toString());
        coverEventRepository.save(coverEvent);
    }

}
