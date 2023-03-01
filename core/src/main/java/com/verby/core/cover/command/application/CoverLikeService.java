package com.verby.core.cover.command.application;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.cover.command.domain.Cover;
import com.verby.core.cover.command.domain.CoverLike;
import com.verby.core.cover.command.domain.CoverLikeRepository;
import com.verby.core.cover.command.domain.CoverRepository;
import com.verby.core.cover.exception.CoverLikeExistsAlreadyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoverLikeService {

    private final CoverLikeRepository likeRepository;
    private final CoverRepository coverRepository;

    public void like(long userId, long coverId) {
        Cover cover = coverRepository.findById(coverId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_NOT_FOUND, "Not found."));

        if(likeRepository.existsByUserIdAndCoverId(userId, coverId)) {
            throw new CoverLikeExistsAlreadyException(ErrorCode.COVER_LIKE_EXISTS_ALREADY);
        }

        CoverLike like = new CoverLike(userId, cover);
        likeRepository.save(like);
    }

    public void unlike(long userId, long coverId) {
        CoverLike like = likeRepository.findByUserIdAndCoverId(userId, coverId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.COVER_LIKE_NOT_FOUND, "Like not found."));

        likeRepository.delete(like);
    }

}
