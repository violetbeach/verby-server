package verby.apiserver.contest.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import verby.apiserver.contest.command.domain.Contest;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatedContestInfo {

    private final long id;
    private final long songId;
    private final String content;
    private final int round;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;

    public static CreatedContestInfo from(Contest contest) {
        return new CreatedContestInfo(
                contest.getId(),
                contest.getSongId(),
                contest.getContent(),
                contest.getRound(),
                contest.getStartTime(),
                contest.getEndTime(),
                contest.getCreatedAt()
        );
    }

}
