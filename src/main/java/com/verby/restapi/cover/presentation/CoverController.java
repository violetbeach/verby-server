package com.verby.restapi.cover.presentation;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.cover.command.application.CoverService;
import com.verby.restapi.cover.command.application.PostCoverRequest;
import com.verby.restapi.cover.command.application.PostedCoverInfo;
import com.verby.restapi.cover.query.dao.CoverSummaryDao;
import com.verby.restapi.cover.query.dao.CoverSummaryQueryDao;
import com.verby.restapi.cover.query.dto.CoverSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverController {

    private final CoverService coverService;
    private final CoverSummaryDao coverSummaryDao;
    private final CoverSummaryQueryDao coverSummaryQueryDao;

    @GetMapping("/{id}")
    private ResponseEntity<CoverSummary> findById(@PathVariable long id) {
        CoverSummary coverSummary = coverSummaryDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));

        return new ResponseEntity<>(coverSummary, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<CoverSummary>> noOffsetSearch(@RequestParam(required = false) Long coverIdLt, @RequestParam int pageSize) {
        List<CoverSummary> coverSummaries = coverSummaryQueryDao.noOffsetSearch(coverIdLt, pageSize);
        return new ResponseEntity<>(coverSummaries, HttpStatus.OK);
    }

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

        PostedCoverInfo cover = coverService.post(request);

        return new ResponseEntity<>(cover, HttpStatus.ACCEPTED);
    }

}
