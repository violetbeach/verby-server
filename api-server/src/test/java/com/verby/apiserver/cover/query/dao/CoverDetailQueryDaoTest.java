package com.verby.apiserver.cover.query.dao;


import com.verby.core.util.pagination.CursorRequest;
import com.verby.core.config.QueryDslConfig;
import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dao.CoverDetailQueryDao;
import com.verby.core.cover.query.dto.CoverDetailQueryModel;
import com.verby.apiserver.support.fixture.domain.CoverFixture;
import com.verby.core.cover.Cover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import({QueryDslConfig.class, CoverDetailQueryDao.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CoverDetailQueryDao의")
class CoverDetailQueryDaoTest {

    @Autowired
    private CoverDetailQueryDao coverDetailQueryDao;

    @Autowired
    private TestEntityManager em;

    @Nested
    @DisplayName("findAll 메서드는")
    class findAll {

        @Test
        @DisplayName("CoverDetailQueryModel을 전체 조회한다.")
        void firstPage() {
            // given
            String prefix = "title";
            for(int i = 1; i <= 30; i++) {
                Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 1L, prefix + i);
                em.persistAndFlush(cover);
            }

            CursorRequest cursor = new CursorRequest(null, 10);
            CoverSearchRequest request = new CoverSearchRequest(null);

            request.setCursor(cursor);

            // when
            List<CoverDetailQueryModel> coverDetailQueryModels = coverDetailQueryDao.findAll(request);

            // then
            assertAll(
                    () -> assertThat(coverDetailQueryModels).hasSize(10),
                    () -> assertThat(coverDetailQueryModels.get(0).getTitle()).isEqualTo("title30"),
                    () -> assertThat(coverDetailQueryModels.get(9).getTitle()).isEqualTo("title21")
            );
        }

    }

}