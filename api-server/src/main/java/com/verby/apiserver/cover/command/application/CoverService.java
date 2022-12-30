package com.verby.apiserver.cover.command.application;

import com.verby.apiserver.cover.command.domain.CoverRepository;
import com.verby.core.cover.Cover;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverRepository coverRepository;

    @Transactional
    public PostedCoverInfo create(PostCoverRequest request) {
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
