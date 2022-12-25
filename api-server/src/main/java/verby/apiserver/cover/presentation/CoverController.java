package verby.apiserver.cover.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import verby.apiserver.common.util.ip.ClientIPUtils;
import verby.apiserver.common.util.pagination.CursorRequest;
import verby.apiserver.common.util.pagination.CursorResponse;
import verby.apiserver.config.security.SecurityUser;
import verby.apiserver.cover.command.application.CoverSearchRequest;
import verby.apiserver.cover.command.application.CoverService;
import verby.apiserver.cover.command.application.PostCoverRequest;
import verby.apiserver.cover.command.application.PostedCoverInfo;
import verby.apiserver.cover.query.application.CoverSummaryQueryService;
import verby.apiserver.cover.query.dto.CoverDetailQueryModel;
import verby.apiserver.cover.query.dto.CoverQueryModel;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
        request.setRequestedBy(ClientIPUtils.getRemoteIP(servletRequest));

        PostedCoverInfo cover = coverService.create(request);
        return new ResponseEntity<>(cover, HttpStatus.CREATED);
    }

}
