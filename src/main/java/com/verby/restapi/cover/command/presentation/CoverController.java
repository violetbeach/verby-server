package com.verby.restapi.cover.command.presentation;

import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.cover.command.application.CoverService;
import com.verby.restapi.cover.command.application.PostCoverRequest;
import com.verby.restapi.cover.command.application.PostedCoverInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverController {

    private final CoverService coverService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    private ResponseEntity<PostedCoverInfo> post(@AuthenticationPrincipal SecurityUser user,
                                                 @RequestPart @Valid PostCoverRequest request,
                                                 @RequestPart(value = "video") MultipartFile video,
                                                 @RequestPart(value = "highlight") MultipartFile highlight,
                                                 @RequestPart(value = "image") MultipartFile image) {
        request.setUserId(user.getUserId());
        request.setVideo(video);
        request.setHighlight(highlight);
        request.setImage(image);

        PostedCoverInfo cover = coverService.upload(request);

        return new ResponseEntity<>(cover, HttpStatus.ACCEPTED);
    }

}
