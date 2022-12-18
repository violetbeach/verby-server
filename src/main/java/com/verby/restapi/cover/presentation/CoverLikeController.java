package com.verby.restapi.cover.presentation;

import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.cover.command.application.CoverLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverLikeController {

    private final CoverLikeService likeService;

    @PostMapping("/{coverId}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@AuthenticationPrincipal SecurityUser user, @PathVariable Long coverId) {
        likeService.like(user.getUserId(), coverId);
    }

    @DeleteMapping("/{coverId}/likes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlike(@AuthenticationPrincipal SecurityUser user, @PathVariable Long coverId) {
        likeService.unlike(user.getUserId(), coverId);
    }

}
