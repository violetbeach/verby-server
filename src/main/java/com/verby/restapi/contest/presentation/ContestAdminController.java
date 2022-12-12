package com.verby.restapi.contest.presentation;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.contest.command.application.ContestService;
import com.verby.restapi.contest.command.application.CreateContestRequest;
import com.verby.restapi.contest.command.application.CreatedContestInfo;
import com.verby.restapi.contest.command.domain.SongService;
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
    private final SongService songService;

    @PostMapping("/contests")
    private ResponseEntity<CreatedContestInfo> create(@RequestBody @Valid CreateContestRequest createContestRequest) {
        if(!songService.existsSong(createContestRequest.getSongId())) {
            throw new EntityNotFoundException(ErrorCode.SONG_NOT_FOUND, "Not found.");
        }

        CreatedContestInfo contest = contestService.create(createContestRequest);
        return new ResponseEntity<>(contest, HttpStatus.CREATED);
    }

}
