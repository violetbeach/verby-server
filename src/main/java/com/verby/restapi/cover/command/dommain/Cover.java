package com.verby.restapi.cover.command.dommain;

import com.verby.restapi.common.domain.BaseEntity;
import com.verby.restapi.contest.exception.InvalidContestDateException;
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
public class Cover extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long contestId;

    private long userId;

    private String title;

    private String video;

    private String highlight;

    private String image;

    private long hits;

    public Cover(long contestId, long userId, String title, String video, String highlight, String image) {
        this.contestId = contestId;
        this.userId = userId;
        this.title = title;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
    }
}
