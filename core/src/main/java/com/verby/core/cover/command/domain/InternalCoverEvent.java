package com.verby.core.cover.command.domain;

import com.verby.core.common.event.internal.InternalEvent;
import com.verby.core.cover.command.application.CoverEventType;
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
@Table(name = "cover_event")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InternalCoverEvent extends InternalEvent {
    private Long coverId;
    @Enumerated(value = EnumType.STRING)
    private CoverEventType type;
    private String attributes;
    @CreatedDate
    private LocalDateTime createdAt;
    private String requestedBy;

    public InternalCoverEvent(Long coverId, CoverEventType type, String attributes) {
        this.coverId = coverId;
        this.type = type;
        this.attributes = attributes;
        this.isPublished = false;
    }

}
