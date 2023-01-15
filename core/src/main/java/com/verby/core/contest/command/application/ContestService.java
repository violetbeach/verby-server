package com.verby.core.contest.command.application;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.contest.command.domain.Contest;
import com.verby.core.contest.command.domain.ContestRepository;
import com.verby.core.contest.command.domain.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;
    private final SongService songService;

    public CreatedContestInfo create(CreateContestRequest createContestRequest) {
        if(!songService.existsSong(createContestRequest.getSongId())) {
            throw new EntityNotFoundException(ErrorCode.SONG_NOT_FOUND, "Not found.");
        }

        Contest contest = new Contest(
                createContestRequest.getSongId(),
                createContestRequest.getContent(),
                createContestRequest.getRound(),
                createContestRequest.getStartTime(),
                createContestRequest.getEndTime()
        );

        contestRepository.save(contest);

        return CreatedContestInfo.from(contest);
    }

}
