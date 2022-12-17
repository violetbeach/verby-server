package com.verby.restapi.cover.presentation;

import com.verby.restapi.common.util.pagination.CursorRequest;
import com.verby.restapi.common.util.pagination.CursorResponse;
import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.cover.command.application.CoverService;
import com.verby.restapi.cover.command.application.PostCoverRequest;
import com.verby.restapi.cover.command.application.PostedCoverInfo;
import com.verby.restapi.cover.query.application.CoverSummaryQueryService;
import com.verby.restapi.cover.query.dto.CoverDetailQueryModel;
import com.verby.restapi.cover.query.dto.CoverQueryModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.verby.restapi.common.util.ip.ClientIPUtils.getRemoteIP;

@RestController
@RequestMapping("/covers")
@RequiredArgsConstructor
public class CoverController {

    private final CoverService coverService;
    private final CoverSummaryQueryService coverSummaryQueryService;

    @GetMapping("/{id}")
    private ResponseEntity<CoverQueryModel> findById(@PathVariable long id) {
        CoverQueryModel coverQueryModel = coverSummaryQueryService.findById(id);
        return new ResponseEntity<>(coverQueryModel, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<CursorResponse<CoverDetailQueryModel>> findAll(
            CoverSearchRequest request, CursorRequest cursor) {
        request.setCursor(cursor);
        List<CoverDetailQueryModel> coverDetailQueryModels = coverSummaryQueryService.findAll(request);

        long nextKey = coverDetailQueryModels.stream()
                .mapToLong(CoverDetailQueryModel::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new ResponseEntity<>(
                new CursorResponse<>(coverDetailQueryModels, cursor.next(nextKey)), HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    private ResponseEntity<PostedCoverInfo> create(@AuthenticationPrincipal SecurityUser user,
                                                   @RequestBody @Valid PostCoverRequest request,
                                                   HttpServletRequest servletRequest) {
        request.setUserId(user.getUserId());
        request.setRequestedBy(getRemoteIP(servletRequest));

        PostedCoverInfo cover = coverService.create(request);
        return new ResponseEntity<>(cover, HttpStatus.CREATED);
    }

}
