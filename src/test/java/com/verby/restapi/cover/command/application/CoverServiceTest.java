package com.verby.restapi.cover.command.application;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.command.domain.CoverRepository;
import com.verby.restapi.user.command.application.UploadedResourcesPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("CoverService의")
class CoverServiceTest {

    @InjectMocks
    CoverService coverService;
    @Mock
    CoverStorageService storageService;
    @Mock
    CoverRepository coverRepository;

    @Nested
    @DisplayName("post 메서드는")
    class post {

        @Test
        @DisplayName("PostCoverRequest로 해당 자원들을 스토리지에 업로드한다.")
        void success() {
            // given
            PostCoverRequest request = new PostCoverRequest(1L, "커버 영상 제목입니다.");
            request.setVideo(new MockMultipartFile("video", "cover.mp4", "video/mp4",  "Video Binary Data".getBytes()));
            request.setHighlight(new MockMultipartFile("highlight", "highlight.mp4", "video/mp4", "Video Binary Data".getBytes()));
            request.setImage(new MockMultipartFile("image", "thumbnail.png", "image/png", "Image Binary Data".getBytes()));

            UploadedResourcesPath uploadedPaths = new UploadedResourcesPath("/static/cover/video/11039", "/static/cover/highlight/13092", "/static/cover/image/33242");
            given(storageService.uploads(any()))
                    .willReturn(uploadedPaths);

            given(coverRepository.save(any(Cover.class)))
                    .will(AdditionalAnswers.returnsFirstArg());

            // when
            PostedCoverInfo cover = coverService.post(request);

            // given
            assertAll(
                    () -> assertThat(cover.getTitle()).isEqualTo(request.getTitle()),
                    () -> assertThat(cover.getContestId()).isEqualTo(request.getContestId()),
                    () -> assertThat(cover.getVideo()).isEqualTo(uploadedPaths.getVideo()),
                    () -> assertThat(cover.getHighlight()).isEqualTo(uploadedPaths.getHighlight()),
                    () -> assertThat(cover.getImage()).isEqualTo(uploadedPaths.getImage())
            );
        }

    }

}