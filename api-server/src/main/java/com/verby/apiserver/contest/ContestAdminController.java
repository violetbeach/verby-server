package com.verby.apiserver.contest;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.contest.command.application.ContestService;
import com.verby.core.contest.command.application.CreateContestRequest;
import com.verby.core.contest.command.application.CreatedContestInfo;
import com.verby.core.contest.command.domain.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ContestAdminController {

    private final ContestService contestService;

    @PostMapping("/contests")
    private ResponseEntity<CreatedContestInfo> create(@RequestBody @Valid CreateContestRequest createContestRequest) {
        CreatedContestInfo contest = contestService.create(createContestRequest);
        return new ResponseEntity<>(contest, HttpStatus.CREATED);
    }

}
