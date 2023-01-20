package com.verby.internalconsumerserver.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverUpdatedEventHandler {

    private final CoverQueryModelService coverSummaryService;
    private final CoverQueryModelRepository queryModelWriteDao;

    public void handle(Long coverId) {
        CoverQueryModel queryModel = coverSummaryService.getQueryModel(coverId);
        queryModelWriteDao.save(queryModel);
    }

}