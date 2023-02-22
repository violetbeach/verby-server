package com.verby.core.covercomment.command.domain;

import com.verby.core.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_to")
    private CoverComment replyTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "replyTo", cascade = CascadeType.ALL)
    private List<CoverComment> replies;

    public CoverComment(long coverId, long commenterId, String content) {
        this.coverId = coverId;
        this.commenterId = commenterId;
        this.content = content;
    }
}
