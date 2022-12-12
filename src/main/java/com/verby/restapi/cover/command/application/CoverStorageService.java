package com.verby.restapi.cover.command.application;

import com.verby.restapi.common.storage.dto.Domain;
import com.verby.restapi.common.storage.dto.Resource;
import com.verby.restapi.common.storage.exception.ResourceTypeNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class CoverStorageService {
    private final CoverStoragePathProperties storagePathProperties;
    abstract public String getPreSignedUrl(Resource resource);

    protected String getResourcePath(Resource resource) {
        switch (resource) {
            case FULL_VIDEO -> {
                return storagePathProperties.getVideo();
            }
            case HIGHLIGHT -> {
                return storagePathProperties.getHighlight();
            }
            case IMAGE -> {
                return storagePathProperties.getImage();
            }
            default -> {
                log.warn("Not allow resource Type (Cover -> {}).", resource);
                throw new ResourceTypeNotMatchException(Domain.COVER, resource);
            }
        }
    }

}
