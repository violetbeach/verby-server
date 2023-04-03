package com.verby.core.song.command.application;

import com.verby.core.storage.dto.Domain;
import com.verby.core.storage.dto.Resource;
import com.verby.core.storage.exception.ResourceTypeNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public abstract class SongStorageService {
    @Value("${static.paths.song.image}")
    private String imageBasePath;
    abstract public String getPreSignedUrl(Resource resource, String fileName);

    protected String getResourcePath(Resource resource) {
        if (resource == Resource.IMAGE) {
            return imageBasePath;
        }
        log.warn("Not allow resource Type (Song -> {}).", resource);
        throw new ResourceTypeNotMatchException(Domain.SONG, resource);
    }

}