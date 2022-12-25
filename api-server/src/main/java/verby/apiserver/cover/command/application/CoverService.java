package verby.apiserver.cover.command.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import verby.apiserver.cover.command.domain.CoverRepository;
import verby.core.cover.Cover;

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
