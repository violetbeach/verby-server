package com.verby.restapi.cover.command.application;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.cover.command.domain.CoverLike;
import com.verby.restapi.cover.command.domain.CoverLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoverLikeService {

    private final CoverLikeRepository likeRepository;

    public void like(long userId, long coverId) {
        CoverLike like = new CoverLike(userId, coverId);
        likeRepository.save(like);
    }

    public void unlike(long userId, long coverId) {
        CoverLike like = likeRepository.findByUserIdAndCoverId(userId, coverId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.LIKE_NOT_FOUND, "Like not found."));
        likeRepository.delete(like);
    }

}
