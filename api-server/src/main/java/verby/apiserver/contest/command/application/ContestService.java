package verby.apiserver.contest.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.contest.command.domain.Contest;
import verby.apiserver.contest.command.domain.ContestRepository;

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
