package com.verby.batchserver.cover;

import com.verby.core.cover.Cover;
import com.verby.core.cover.CoverHit;
import com.verby.core.cover.command.domain.CoverHitRepository;
import com.verby.core.cover.command.domain.CoverRepository;
import config.database.EmbeddedRedisConfig;
import fixture.CoverFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@Import(EmbeddedRedisConfig.class)
@DisplayName("WriteBackCoverHitsScheduler 클래스의")
class WriteBackCoverHitsSchedulerTest {
    @Autowired
    WriteBackCoverHitsScheduler writeBackCoverHitsScheduler;
    @Autowired
    CoverHitRepository coverHitRepository;
    @Autowired
    CoverRepository coverRepository;

    @Nested
    @DisplayName("execute 메서드는")
    class execute {

        @Test
        @DisplayName("쌓인 조회 수를 커버에 반영한다.")
        void ItWriteBackHits() {
            Cover cover = CoverFixture.NORMAL_COVER.getCover();
            coverRepository.save(cover);

            CoverHit coverHit = new CoverHit(cover.getId());
            for (int i = 0; i < 100; i++) {
                coverHit.hit("192.168.0." + i);
            }
            coverHitRepository.save(coverHit);

            // when
            writeBackCoverHitsScheduler.execute();

            // then
            Cover newCover = coverRepository.findById(cover.getId()).get();
            assertThat(newCover.getHits()).isEqualTo(100L);
        }

    }

}