package com.verby.core.covercomment.command.domain;

import com.verby.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoverComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long coverId;

    @Column(name = "user_id")
    private long commenterId;

    private String content;

    private Long replyTo;

    public CoverComment(long coverId, long commenterId, String content, Long replyTo) {
        this.coverId = coverId;
        this.commenterId = commenterId;
        this.content = content;
        this.replyTo = replyTo;
    }
}
