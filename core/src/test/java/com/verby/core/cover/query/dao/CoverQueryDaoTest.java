package com.verby.core.cover.query.dao;

import com.verby.core.cover.query.dto.CoverQueryModel;
import config.database.EmbeddedMongoConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import static fixture.CoverQueryModelFixture.NORMAL_COVER_QUERY_MODEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Import({EmbeddedMongoConfig.class})
@DataMongoTest
@DisplayName("CoverSummaryQueryDao의")
class CoverQueryDaoTest {
    @Autowired
    CoverQueryDao coverQueryDao;
    @Autowired
    MongoTemplate mongoTemplate;

    @Nested
    @DisplayName("findById 메서드는")
    class findById {

        @Test
        @DisplayName("coverId로 CoverSummary를 조회한다.")
        void ItReturnCoverSummary() {
            CoverQueryModel insert = NORMAL_COVER_QUERY_MODEL.getCoverQueryModel(1L);
            mongoTemplate.insert(insert);

            // when
            CoverQueryModel result = coverQueryDao.findById(insert.getId()).get();

            // then
            assertAll(
                    () -> assertThat(result.getTitle()).isEqualTo(insert.getTitle()),
                    () -> assertThat(result.getVideo()).isEqualTo(insert.getVideo()),
                    () -> assertThat(result.getHighlight()).isEqualTo(insert.getHighlight()),
                    () -> assertThat(result.getImage()).isEqualTo(insert.getImage()),

                    () -> assertThat(result.getPublisherId()).isEqualTo(insert.getPublisherId()),
                    () -> assertThat(result.getPublisherName()).isEqualTo(insert.getPublisherName()),

                    () -> assertThat(result.getContestId()).isEqualTo(insert.getContestId()),

                    () -> assertThat(result.getArtistId()).isEqualTo(insert.getArtistId()),
                    () -> assertThat(result.getArtistName()).isEqualTo(insert.getArtistName()),

                    () -> assertThat(result.getSongId()).isEqualTo(insert.getSongId()),
                    () -> assertThat(result.getSongName()).isEqualTo(insert.getSongName())
            );
        }

    }


}