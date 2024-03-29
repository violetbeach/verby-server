package com.verby.core.cover.command.application;

import com.verby.core.cover.command.domain.ContestService;
import com.verby.core.cover.command.domain.Cover;
import com.verby.core.cover.command.domain.CoverRepository;
import com.verby.core.user.command.application.UploadedResourcesPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    CoverRepository coverRepository;

    @Mock
    ContestService contestService;

    @Nested
    @DisplayName("create 메서드는")
    class create {

        @Test
        @DisplayName("PostCoverRequest로 해당 자원들을 스토리지에 업로드한다.")
        void ItReturnPostedCoverInfo() {
            // given
            PostCoverRequest request = new PostCoverRequest(
                    1L,
                    "커버 영상 제목입니다.",
                    "커버 영상 설명",
                    "/static/cover/video/sample.mp4",
                    "/static/cover/highlight/sample.mp4",
                    "/static/cover/image/sample.png");

            UploadedResourcesPath uploadedPaths = new UploadedResourcesPath("/static/cover/video/11039", "/static/cover/highlight/13092", "/static/cover/image/33242");

            given(contestService.existsById(request.getContestId()))
                    .willReturn(true);

            given(coverRepository.save(any(Cover.class)))
                    .will(AdditionalAnswers.returnsFirstArg());

            // when
            PostedCoverInfo cover = coverService.create(request);

            // given
            assertAll(
                    () -> assertThat(cover.getTitle()).isEqualTo(request.getTitle()),
                    () -> assertThat(cover.getContestId()).isEqualTo(request.getContestId()),
                    () -> assertThat(cover.getVideo()).isEqualTo(request.getVideo()),
                    () -> assertThat(cover.getHighlight()).isEqualTo(request.getHighlight()),
                    () -> assertThat(cover.getImage()).isEqualTo(request.getImage())
            );
        }

    }

}