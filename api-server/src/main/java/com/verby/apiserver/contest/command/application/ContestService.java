package com.verby.apiserver.contest.command.application;

import com.verby.apiserver.contest.command.domain.Contest;
import com.verby.apiserver.contest.command.domain.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

    public CreatedContestInfo create(CreateContestRequest createContestRequest) {

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
