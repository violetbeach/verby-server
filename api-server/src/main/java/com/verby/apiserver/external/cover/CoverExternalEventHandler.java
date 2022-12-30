package com.verby.apiserver.external.cover;

import com.verby.apiserver.cover.command.domain.CoverEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class CoverExternalEventHandler {

    private final ExternalCoverSummaryService coverSummaryService;
    private final ExternalCoverQueryDao queryModelWriteDao;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(CoverEvent event) {
        ExternalCoverQueryModel queryModel = coverSummaryService.getQueryModel(event.getCoverId());
        queryModelWriteDao.save(queryModel);
    }

}
