package com.verby.restapi.external.cover;

import com.verby.restapi.cover.command.domain.CoverEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
public class CoverExternalEventHandler {

    private final CoverSummaryService coverSummaryService;
    private final CoverQueryModelWriteDao queryModelWriteDao;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(CoverEvent event) {
        CoverQueryModel queryModel = coverSummaryService.getQueryModel(event.getCoverId());
        queryModelWriteDao.save(queryModel);
    }

}
