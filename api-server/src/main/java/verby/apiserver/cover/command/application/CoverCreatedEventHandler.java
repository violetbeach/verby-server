package verby.apiserver.cover.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import verby.apiserver.cover.command.domain.CoverEvent;
import verby.apiserver.cover.command.domain.CoverEventRepository;
import verby.core.common.event.Events;
import verby.core.cover.Cover;
import verby.core.cover.CoverCreatedEvent;

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
        Events.raise(coverEvent);
    }

}
