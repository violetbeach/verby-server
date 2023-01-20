package com.verby.core.contest.query;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "contest")
public class ContestData {

    @Id
    private Long id;
    private long songId;
    private String content;
    private int round;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
