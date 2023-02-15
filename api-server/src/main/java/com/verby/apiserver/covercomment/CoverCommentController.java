package com.verby.apiserver.covercomment;

import com.verby.apiserver.config.security.SecurityUser;
import com.verby.core.covercomment.command.application.CommentInfo;
import com.verby.core.covercomment.command.application.CommentRequest;
import com.verby.core.covercomment.command.application.CoverCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/covers/{coverId}/comments")
@RequiredArgsConstructor
public class CoverCommentController {

    private final CoverCommentService commentService;

    @PostMapping
    private ResponseEntity<CommentInfo.Posted> create(@AuthenticationPrincipal SecurityUser user,
                                               @PathVariable Long coverId,
                                               @RequestBody @Valid CommentRequest request) {
        request.setCoverId(coverId);
        request.setUserId(user.getUserId());

        CommentInfo.Posted commentedInfo = commentService.create(request);
        return new ResponseEntity<>(commentedInfo, HttpStatus.CREATED);
    }

}
