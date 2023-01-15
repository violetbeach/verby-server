package com.verby.core.cover.command.application;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityNotFoundException;
import com.verby.core.cover.command.domain.ContestService;
import com.verby.core.cover.command.domain.Cover;
import com.verby.core.cover.command.domain.CoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoverService {
    private final CoverRepository coverRepository;
    private final ContestService contestService;

    @Transactional
    public PostedCoverInfo create(PostCoverRequest request) {
        if(!contestService.existsById(request.getContestId())) {
            throw new EntityNotFoundException(ErrorCode.CONTEST_NOT_FOUND, "Not found.");
        }
        Cover cover = new Cover(
                request.getContestId(),
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getVideo(),
                request.getHighlight(),
                request.getImage()
        );

        coverRepository.save(cover);

        return PostedCoverInfo.from(cover);
    }

}
