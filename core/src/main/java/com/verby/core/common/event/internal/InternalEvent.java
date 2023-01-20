package com.verby.core.common.event.internal;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class InternalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected boolean isPublished;
    private LocalDateTime publishedAt;

    public void publish() {
        this.isPublished = true;
        this.publishedAt = LocalDateTime.now();
    }
}
