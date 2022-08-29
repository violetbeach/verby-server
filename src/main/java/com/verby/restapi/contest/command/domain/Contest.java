package com.verby.restapi.contest.command.domain;

import com.verby.restapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long songId;

    private String content;

    private int round;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public Contest(long songId, String content, int round, LocalDateTime startTime, LocalDateTime endTime) {
        this.songId = songId;
        this.content = content;
        this.round = round;
        setContestDate(startTime, endTime);
    }

    private void setContestDate(LocalDateTime startTime, LocalDateTime endTime) {
        if(!startTime.isBefore(endTime)) {
            throw  new IllegalArgumentException("Start time is later thane end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
