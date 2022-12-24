package verby.apiserver.cover.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.EntityNotFoundException;
import verby.apiserver.cover.command.domain.CoverLike;
import verby.apiserver.cover.command.domain.CoverLikeRepository;

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
