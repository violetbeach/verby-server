package verby.apiserver.inquiry.command.domain;

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
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long inquirerId;

    private Long answererId;

    private String title;

    private String content;

    private String answer;

    @Enumerated(EnumType.STRING)
    private InquiryStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime answeredAt;

    public Inquiry(long inquirerId, String title, String content) {
        this.inquirerId = inquirerId;
        this.title = title;
        this.content = content;
        this.status = InquiryStatus.WAITING_ANSWER;
    }
}
