package com.verby.internalconsumerserver.cover;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverUpdatedEventHandler {

    private final CoverQueryModelService coverSummaryService;
    private final CoverQueryModelRepository queryModelWriteDao;


    @CachePut(value = "Cover", key = "#id")
    public CoverQueryModel handle(Long id) {
        CoverQueryModel queryModel = coverSummaryService.getQueryModel(id);
        return queryModelWriteDao.save(queryModel);
    }

}