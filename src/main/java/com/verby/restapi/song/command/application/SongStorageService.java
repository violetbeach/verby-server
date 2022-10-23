package com.verby.restapi.song.command.application;

import com.verby.restapi.common.storage.StaticStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SongStorageService {

    private final StaticStorage staticStorage;

    @Value("${static.paths.song.image}")
    private String imageBasePath;

    public String upload(MultipartFile image) {
        return staticStorage.upload(image, imageBasePath);
    }


}
