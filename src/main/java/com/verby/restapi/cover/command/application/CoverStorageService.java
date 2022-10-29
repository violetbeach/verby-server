package com.verby.restapi.cover.command.application;

import com.verby.restapi.common.storage.StaticStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class CoverStorageService {

    private final StaticStorage staticStorage;
    private final CoverStoragePathProperties storagePathProperties;

    public CoverStoragePathProperties uploads(UploadCoverResourceRequest request) {
        return new CoverStoragePathProperties(
                uploadVideo(request.getVideo()),
                uploadHighlight(request.getHighlight()),
                uploadImage(request.getImage())
        );
    }

    private String uploadVideo(MultipartFile video) {
        return staticStorage.upload(video, storagePathProperties.getVideo());
    }

    private String uploadHighlight(MultipartFile highlight) {
        return staticStorage.upload(highlight, storagePathProperties.getHighlight());
    }

    private String uploadImage(MultipartFile image) {
        return staticStorage.upload(image, storagePathProperties.getImage());
    }


}
