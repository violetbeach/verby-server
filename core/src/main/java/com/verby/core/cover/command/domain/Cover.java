package com.verby.core.cover.command.domain;

import com.verby.core.common.domain.BaseEntity;
import com.verby.core.common.event.Events;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cover extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long contestId;

    @Column(name = "user_id")
    private long publisherId;

    private String title;

    private String content;

    private String video;

    private String highlight;

    private String image;

    private long hits;

    public Cover(long contestId, long publisherId, String title, String content, String video, String highlight, String image) {
        this.contestId = contestId;
        this.publisherId = publisherId;
        this.title = title;
        this.content = content;
        this.video = video;
        this.highlight = highlight;
        this.image = image;
        this.hits = 0;
    }

    public void hit(long hit) {
        this.hits += hit;
    }

    @PostPersist
    private void onPostPersist() {
        Events.raise(new CoverUpdatedEvent(this));
    }

    @PostUpdate
    private void onPostUpdate() {
        Events.raise(new CoverUpdatedEvent(this));
    }

}
