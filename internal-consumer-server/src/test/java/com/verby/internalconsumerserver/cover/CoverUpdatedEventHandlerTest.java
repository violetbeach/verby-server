package com.verby.internalconsumerserver.cover;

import com.verby.internalconsumerserver.cover.infra.client.CoverSummaryDao;
import com.verby.internalconsumerserver.cover.infra.dto.CoverSummary;
import com.verby.internalconsumerserver.support.repository.TestCoverQueryModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("CoverUpdatedEventHandler의")
class CoverUpdatedEventHandlerTest {

    @Autowired
    CoverUpdatedEventHandler handler;
    @Autowired
    TestCoverQueryModelRepository queryModelRepository;
    @MockBean
    CoverSummaryDao coverSummaryDao;

    @Nested
    @DisplayName("handle 메서드는")
    class handle {
        CoverSummary coverSummary;

        @BeforeEach
        void setup() {
            coverSummary = new CoverSummary(
                    1L,
                    2L,
                    3L,
                    "꿈꾸는 소년",
                    "커버 영상 제목",
                    "커버 영상 부연 설명",
                    "/static/video",
                    "/static/highlight",
                    "/static/image",
                    4L,
                    "가수 제목",
                    5L,
                    "곡 제목",
                    0L
            );
            given(coverSummaryDao.findById(coverSummary.getId()))
                    .willReturn(coverSummary);

        }

        @Test
        @DisplayName("커버 id로 커버 조회 모델을 생성한다.")
        void ItCreateCoverQueryModel() {
            // when
            handler.handle(coverSummary.getId());

            // then
            CoverQueryModel result = queryModelRepository.findById(coverSummary.getId());

            assertAll(
                    () -> assertThat(result.getId()).isEqualTo(coverSummary.getId()),
                    () -> assertThat(result.getTitle()).isEqualTo(coverSummary.getTitle()),
                    () -> assertThat(result.getContent()).isEqualTo(coverSummary.getContent()),
                    () -> assertThat(result.getContestId()).isEqualTo(coverSummary.getContestId()),
                    () -> assertThat(result.getPublisherId()).isEqualTo(coverSummary.getPublisherId()),
                    () -> assertThat(result.getPublisherName()).isEqualTo(coverSummary.getPublisherName()),
                    () -> assertThat(result.getVideo()).isEqualTo(coverSummary.getVideo()),
                    () -> assertThat(result.getHighlight()).isEqualTo(coverSummary.getHighlight()),
                    () -> assertThat(result.getImage()).isEqualTo(coverSummary.getImage()),
                    () -> assertThat(result.getArtistId()).isEqualTo(coverSummary.getArtistId()),
                    () -> assertThat(result.getArtistName()).isEqualTo(coverSummary.getArtistName()),
                    () -> assertThat(result.getSongId()).isEqualTo(coverSummary.getSongId()),
                    () -> assertThat(result.getSongName()).isEqualTo(coverSummary.getSongName()),
                    () -> assertThat(result.getHits()).isEqualTo(coverSummary.getHits())
            );


        }

    }

}