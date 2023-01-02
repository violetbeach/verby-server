package com.verby.core.contest.command.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateContestRequest {

    private Long songId;
    private String content;
    private int round;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public void setSongId(Long songId) {
        this.songId = songId;
    }
}
