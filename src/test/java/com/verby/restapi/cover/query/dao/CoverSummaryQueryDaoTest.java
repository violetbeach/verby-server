package com.verby.restapi.cover.query.dao;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.query.dto.CoverSummary;
import com.verby.restapi.support.fixture.domain.CoverFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CoverSummaryQueryDao의")
class CoverSummaryQueryDaoTest {

    @Autowired
    private CoverSummaryQueryDao coverSummaryQueryDao;

    @Autowired
    private TestEntityManager em;

    @Nested
    @DisplayName("noOffsetSearch 메서드는")
    class noOffsetSearch {

        void firstPage() {
            // given
            String prefix = "title";

            for(int i = 1; i < 30; i++) {
                Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 1L, prefix + i);
                em.persistAndFlush(cover);
            }

            // when
            List<CoverSummary> coverSummaries = coverSummaryQueryDao.noOffsetSearch(null, 10);

            // then
            assertAll(
                    () -> assertThat(coverSummaries).hasSize(10),
                    () -> assertThat(coverSummaries.get(0).getTitle()).isEqualTo("title30"),
                    () -> assertThat(coverSummaries.get(9).getTitle()).isEqualTo("title21")
            );
        }

    }
}