package com.verby.restapi.song.command.application;

import com.verby.restapi.common.storage.dto.Domain;
import com.verby.restapi.common.storage.dto.Resource;
import com.verby.restapi.common.storage.exception.ResourceTypeNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public abstract class SongStorageService {
    @Value("${static.paths.song.image}")
    private String imageBasePath;
    abstract public String getPreSignedUrl(Resource resource);

    protected String getResourcePath(Resource resource) {
        if (resource == Resource.IMAGE) {
            return imageBasePath;
        }
        log.warn("Not allow resource Type (Song -> {}).", resource);
        throw new ResourceTypeNotMatchException(Domain.SONG, resource);
    }

}