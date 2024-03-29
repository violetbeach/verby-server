package com.verby.core.inquiry.query.dto;

import com.verby.core.inquiry.command.domain.InquiryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inquiry")
public class InquiryData {

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

    private LocalDateTime createdAt;

    private LocalDateTime answeredAt;
}
