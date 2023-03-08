package com.verby.core.covercomment.command.application;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.covercomment.command.domain.CoverComment;
import com.verby.core.covercomment.command.domain.CoverCommentRepository;
import com.verby.core.covercomment.command.domain.CoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoverCommentService {
    private final CoverCommentRepository commentRepository;
    private final CoverService coverService;

    @Transactional
    public CommentInfo.Posted create(CommentRequest request) {
        if(!coverService.existsById(request.getCoverId())) {
            throw new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found.");
        }

        CoverComment coverComment = new CoverComment(
                request.getCoverId(),
                request.getUserId(),
                request.getContent()
        );

        commentRepository.save(coverComment);

        return CommentInfo.Posted.from(coverComment);
    }

}
