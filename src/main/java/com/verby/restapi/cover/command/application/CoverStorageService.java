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

    public String uploadVideo(MultipartFile video) {
        return staticStorage.upload(video, storagePathProperties.getVideo());
    }

    public String uploadHighlight(MultipartFile highlight) {
        return staticStorage.upload(highlight, storagePathProperties.getHighlight());
    }

    public String uploadImage(MultipartFile image) {
        return staticStorage.upload(image, storagePathProperties.getImage());
    }

}
