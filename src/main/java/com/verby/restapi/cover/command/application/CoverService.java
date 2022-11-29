package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.command.domain.CoverRepository;
import com.verby.restapi.user.command.application.UploadedResourcesPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CoverService {

    private final CoverRepository coverRepository;
    private final CoverStorageService coverStorageService;

    @Transactional
    public PostedCoverInfo post(PostCoverRequest request) {
        UploadCoverResourceRequest uploadRequest = new UploadCoverResourceRequest(
                request.getVideo(),
                request.getHighlight(),
                request.getImage()
        );

        UploadedResourcesPath resourcesPath = coverStorageService.uploads(uploadRequest);

        Cover cover = new Cover(
                request.getContestId(),
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                resourcesPath.getVideo(),
                resourcesPath.getHighlight(),
                resourcesPath.getImage()
        );

        coverRepository.save(cover);

        return PostedCoverInfo.from(cover);
    }

}
