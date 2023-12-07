package com.verby.core.cover.query.dao;


import com.verby.core.cover.command.application.CoverSearchRequest;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.support.repository.QueryRepositoryTest;
import com.verby.core.util.pagination.CursorRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static fixture.CoverQueryModelFixture.NORMAL_COVER_QUERY_MODEL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("CoverDetailQueryDao의")
class CustomCoverQueryDaoTest extends QueryRepositoryTest {

    @Autowired
    private CustomCoverQueryDao coverQueryDao;

    @BeforeEach
    void setup() {
        coverQueryDao = new CustomCoverQueryDaoImpl(mongoTemplate);
    }

    @Nested
    @DisplayName("findAll 메서드는")
    class findAll {

        @Test
        @DisplayName("CoverDetailQueryModel을 전체 조회한다.")
        void firstPage() {
            // given
            for(long i = 100; i <= 130; i++) {
                CoverQueryModel coverQueryModel = NORMAL_COVER_QUERY_MODEL.getCoverQueryModel(i);
                mongoTemplate.insert(coverQueryModel);
            }

            CursorRequest cursor = new CursorRequest(null, 10);
            CoverSearchRequest request = new CoverSearchRequest(null);

            request.setCursor(cursor);

            // when
            List<CoverQueryModel> coverQueryModels = coverQueryDao.findAll(request);

            // then
            assertAll(
                    () -> assertThat(coverQueryModels).hasSize(10),
                    () -> assertThat(coverQueryModels.get(0).getId()).isEqualTo(130L),
                    () -> assertThat(coverQueryModels.get(9).getId()).isEqualTo(121L)
            );
        }

    }

}