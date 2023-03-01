package com.verby.core.cover.command.domain;

import com.verby.core.common.event.Events;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoverLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long userId;
    @ManyToOne
    @JoinColumn(name = "cover_id")
    private Cover cover;
    @CreatedDate
    private LocalDateTime createdAt;

    public CoverLike(long userId, Cover cover) {
        this.userId = userId;
        this.cover = cover;
    }

    @PostPersist
    private void onPostPersist() {
        Events.raise(new CoverUpdatedEvent(cover));
    }

    @PostRemove
    private void onPostRemove() {
        Events.raise(new CoverUpdatedEvent(cover));
    }
}
