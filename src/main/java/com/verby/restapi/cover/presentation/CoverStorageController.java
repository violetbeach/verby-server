package com.verby.restapi.cover.presentation;

import com.verby.restapi.cover.command.application.CoverStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverStorageController {

    private final CoverStorageService coverStorageService;

    @PostMapping("/videos")
    private ResponseEntity<String> uploadVideo(@RequestPart(value = "video") MultipartFile video) {
        String uploadPath = coverStorageService.uploadVideo(video);
        return new ResponseEntity<>(uploadPath, HttpStatus.ACCEPTED);
    }

    @PostMapping("/highlights")
    private ResponseEntity<String> uploadHighlight(@RequestPart(value = "highlight") MultipartFile highlight) {
        String uploadPath = coverStorageService.uploadVideo(highlight);
        return new ResponseEntity<>(uploadPath, HttpStatus.ACCEPTED);
    }

    @PostMapping("/images")
    private ResponseEntity<String> uploadImage(@RequestPart(value = "image") MultipartFile image) {
        String uploadPath = coverStorageService.uploadVideo(image);
        return new ResponseEntity<>(uploadPath, HttpStatus.ACCEPTED);
    }

}
