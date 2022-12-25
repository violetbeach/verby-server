package verby.apiserver.cover.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import verby.apiserver.cover.command.application.CoverEventType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoverEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long coverId;
    @Enumerated(value = EnumType.STRING)
    private CoverEventType type;
    private String attributes;
    @CreatedDate
    private LocalDateTime createdAt;
    private boolean isPublished;
    private LocalDateTime publishedAt;
    private String requestedBy;

    public CoverEvent(long coverId, CoverEventType type, String attributes) {
        this.coverId = coverId;
        this.type = type;
        this.attributes = attributes;
        this.isPublished = false;
    }
}
