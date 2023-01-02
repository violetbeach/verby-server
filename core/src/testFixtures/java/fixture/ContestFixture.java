package fixture;


import com.verby.core.contest.command.domain.Contest;

import java.time.LocalDateTime;

public enum ContestFixture {

    선정곡_IU_좋은날("제 152회 선정곡: IU - 좋은 날", 152,
            LocalDateTime.of(2020, 3, 1, 0, 0), LocalDateTime.of(2020, 3, 7, 23, 59));

    private final String content;
    private final int round;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    ContestFixture(String content, int round, LocalDateTime startTime, LocalDateTime endTime) {
        this.content = content;
        this.round = round;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Contest getContest(long songId) {
        return new Contest(songId, content, round, startTime, endTime);
    }
}
