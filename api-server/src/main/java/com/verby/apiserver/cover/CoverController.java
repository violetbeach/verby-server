package com.verby.apiserver.cover;

import com.verby.apiserver.config.security.SecurityUser;
import com.verby.apiserver.util.ip.ClientIPUtils;
import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.command.application.CoverService;
import com.verby.core.cover.command.application.PostCoverRequest;
import com.verby.core.cover.command.application.PostedCoverInfo;
import com.verby.core.cover.query.application.CoverSummaryQueryService;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.util.pagination.CursorRequest;
import com.verby.core.util.pagination.CursorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/covers")
public class CoverController {

    private final CoverService coverService;
    private final CoverSummaryQueryService coverSummaryQueryService;

    public CoverController(CoverService coverService, CoverSummaryQueryService coverSummaryQueryService) {
        this.coverService = coverService;
        this.coverSummaryQueryService = coverSummaryQueryService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<CoverQueryModel> findById(@PathVariable long id) {
        CoverQueryModel coverQueryModel = coverSummaryQueryService.findById(id);
        return new ResponseEntity<>(coverQueryModel, HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<CursorResponse<CoverQueryModel>> findAll(
            CoverSearchRequest request, CursorRequest cursor) {
        request.setCursor(cursor);
        List<CoverQueryModel> coverQueryModels = coverSummaryQueryService.findAll(request);

        long nextKey = coverQueryModels.stream()
                .mapToLong(CoverQueryModel::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new ResponseEntity<>(
                new CursorResponse<>(coverQueryModels, cursor.next(nextKey)), HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    private ResponseEntity<PostedCoverInfo> create(@AuthenticationPrincipal SecurityUser user,
                                                   @RequestBody @Valid PostCoverRequest request,
                                                   HttpServletRequest servletRequest) {
        request.setUserId(user.getUserId());
        request.setRequestedBy(ClientIPUtils.getRemoteIP(servletRequest));

        PostedCoverInfo cover = coverService.create(request);
        return new ResponseEntity<>(cover, HttpStatus.CREATED);
    }

}
