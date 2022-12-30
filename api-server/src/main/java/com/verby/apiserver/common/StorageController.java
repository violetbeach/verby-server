package com.verby.apiserver.common;

import com.verby.core.storage.dto.CreateUploadUrlRequest;
import com.verby.core.cover.command.application.CoverStorageService;
import com.verby.core.song.command.application.SongStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StorageController {

    private final SongStorageService songStorageService;
    private final CoverStorageService coverStorageService;

    @PostMapping("/upload-urls")
    public ResponseEntity<String> publishPreSignedUrl(@RequestBody CreateUploadUrlRequest request) {
        String preSignedUrl = getPreSignedUrl(request);
        return new ResponseEntity<>(preSignedUrl, HttpStatus.CREATED);
    }

    private String getPreSignedUrl(CreateUploadUrlRequest request) {
        switch (request.getDomainType()) {
            case SONG -> {
                return songStorageService.getPreSignedUrl(request.getResourceType());
            }
            case COVER -> {
                return coverStorageService.getPreSignedUrl(request.getResourceType());
            }
            default -> {
                log.error("Not allow Domain Type ({}).", request.getDomainType());
                throw new IllegalStateException("Can't handle this domain type.");
            }
        }
    }

}