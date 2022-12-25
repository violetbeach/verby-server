package verby.apiserver.song.command.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import verby.apiserver.common.storage.dto.Domain;
import verby.apiserver.common.storage.dto.Resource;
import verby.apiserver.common.storage.exception.ResourceTypeNotMatchException;

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