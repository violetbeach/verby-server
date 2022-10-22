package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.command.domain.CoverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverRepository coverRepository;
    private final CoverStorageService coverStorageService;

    public PostedCoverInfo upload(PostCoverRequest request) {
        UploadCoverResourceRequest uploadRequest = new UploadCoverResourceRequest(
                request.getVideo(),
                request.getHighlight(),
                request.getImage()
        );
        CoverStoragePathProperties resourcesPath = coverStorageService.uploads(uploadRequest);

        CreateCoverEntityRequest createEntityRequest = new CreateCoverEntityRequest(
                request.getContestId(),
                request.getUserId(),
                request.getTitle(),
                resourcesPath.getVideo(),
                resourcesPath.getHighlight(),
                resourcesPath.getImage()
        );

        Cover cover = createCover(createEntityRequest);

        return PostedCoverInfo.from(cover);
    }

    @Transactional
    public Cover createCover(CreateCoverEntityRequest request) {
        Cover cover = new Cover(
                request.getContestId(),
                request.getUserId(),
                request.getTitle(),
                request.getVideo(),
                request.getHighlight(),
                request.getImage()
        );

        return coverRepository.save(cover);
    }

}
