package com.verby.core.covercomment.command.application;

import com.verby.core.covercomment.command.domain.CoverComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentInfo {

    private final long id;
    private final long coverId;
    private final long commenterId;
    private final String content;
    private final Long replyTo;

    @Getter
    public static class Posted extends CommentInfo {
        private final LocalDateTime createdAt;

        public Posted(long id, long coverId, long commenterId, String content, Long replyTo, LocalDateTime createdAt) {
            super(id, coverId, commenterId, content, replyTo);
            this.createdAt = createdAt;
        }

        public static Posted from(CoverComment comment) {
            return new Posted(
                    comment.getId(),
                    comment.getCoverId(),
                    comment.getCommenterId(),
                    comment.getContent(),
                    comment.getReplyTo() != null ? comment.getReplyTo().getId() : null,
                    comment.getCreatedAt());
        }
    }

}
