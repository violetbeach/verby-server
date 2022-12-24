package verby.apiserver.cover.command.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import verby.apiserver.common.storage.dto.Domain;
import verby.apiserver.common.storage.dto.Resource;
import verby.apiserver.common.storage.exception.ResourceTypeNotMatchException;

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
