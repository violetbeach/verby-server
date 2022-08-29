package com.verby.restapi.contest.command.application;

import com.verby.restapi.contest.command.domain.Contest;
import com.verby.restapi.contest.command.domain.ContestRepository;
import com.verby.restapi.contest.exception.InvalidContestDateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestRepository contestRepository;

    public Contest create(CreateContestRequest createContestRequest) {

        if(!createContestRequest.getStartTime().isBefore(createContestRequest.getEndTime())) {
            throw new InvalidContestDateException();
        }

        Contest contest = new Contest(
                createContestRequest.getSongId(),
                createContestRequest.getContent(),
                createContestRequest.getRound(),
                createContestRequest.getStartTime(),
                createContestRequest.getEndTime()
        );

        return contestRepository.save(contest);
    }

}
