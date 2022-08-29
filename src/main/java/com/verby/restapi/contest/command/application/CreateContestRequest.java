package com.verby.restapi.contest.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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
