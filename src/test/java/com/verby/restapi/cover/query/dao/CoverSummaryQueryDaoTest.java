package com.verby.restapi.cover.query.dao;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.config.QueryDslConfig;
import com.verby.restapi.contest.command.domain.Contest;
import com.verby.restapi.cover.command.application.CoverSearchRequest;
import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.query.dto.CoverSummary;
import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.support.fixture.domain.ContestFixture;
import com.verby.restapi.support.fixture.domain.CoverFixture;
import com.verby.restapi.user.command.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static com.verby.restapi.support.fixture.domain.ArtistFixture.IU;
import static com.verby.restapi.support.fixture.domain.CoverFixture.NORMAL_COVER;
import static com.verby.restapi.support.fixture.domain.SongFixture.좋은_날;
import static com.verby.restapi.support.fixture.domain.UserFixture.NORMAL_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import({QueryDslConfig.class, CoverSummaryQueryDao.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CoverSummaryQueryDao의")
class CoverSummaryQueryDaoTest {

    @Autowired
    private CoverSummaryQueryDao coverSummaryQueryDao;

    @Autowired
    private TestEntityManager em;

    @Nested
    @DisplayName("findById 메서드는")
    class findById {

        @Test
        @DisplayName("coverId로 CoverSummary를 조회한다.")
        void ItReturnCoverSummary() {
            User user = 유저_생성();
            Artist artist = 가수_생성();
            Song song = 곡_생성(artist);
            Contest contest = 선정곡_생성(song);
            Cover cover = 커버_영상_생성(user, contest);

            // when
            CoverSummary coverSummary = coverSummaryQueryDao.findById(cover.getId()).get();

            // then
            assertAll(
                    () -> assertThat(coverSummary.getTitle()).isEqualTo(cover.getTitle()),
                    () -> assertThat(coverSummary.getVideo()).isEqualTo(cover.getVideo()),
                    () -> assertThat(coverSummary.getHighlight()).isEqualTo(cover.getHighlight()),
                    () -> assertThat(coverSummary.getImage()).isEqualTo(cover.getImage()),

                    () -> assertThat(coverSummary.getPublisherId()).isEqualTo(user.getId()),
                    () -> assertThat(coverSummary.getPublisherName()).isEqualTo(user.getLoginId()),

                    () -> assertThat(coverSummary.getContestId()).isEqualTo(cover.getContestId()),

                    () -> assertThat(coverSummary.getArtistId()).isEqualTo(artist.getId()),
                    () -> assertThat(coverSummary.getArtistName()).isEqualTo(artist.getName()),

                    () -> assertThat(coverSummary.getSongId()).isEqualTo(song.getId()),
                    () -> assertThat(coverSummary.getSongName()).isEqualTo(song.getName())
            );
        }
    }

    @Nested
    @DisplayName("findAll 메서드는")
    class findAll {

        @Test
        @DisplayName("CoverSummary를 전체 조회한다.")
        void firstPage() {
            // given
            String prefix = "title";
            for(int i = 1; i <= 30; i++) {
                Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 1L, prefix + i);
                em.persistAndFlush(cover);
            }

            CoverSearchRequest request = new CoverSearchRequest(null, null, 10);

            // when
            List<CoverSummary> coverSummaries = coverSummaryQueryDao.findAll(request);

            // then
            assertAll(
                    () -> assertThat(coverSummaries).hasSize(10),
                    () -> assertThat(coverSummaries.get(0).getTitle()).isEqualTo("title30"),
                    () -> assertThat(coverSummaries.get(9).getTitle()).isEqualTo("title21")
            );
        }

    }

    private Cover 커버_영상_생성(User user, Contest contest) {
        Cover cover = NORMAL_COVER.getCover(contest.getId(), user.getId());
        em.persistAndFlush(cover);
        return cover;
    }

    private Contest 선정곡_생성(Song song) {
        Contest contest = ContestFixture.선정곡_IU_좋은날.getContest(song.getId());
        em.persistAndFlush(contest);
        return contest;
    }

    private Song 곡_생성(Artist artist) {
        Song song = 좋은_날.getSong(artist.getId());
        em.persistAndFlush(song);
        return song;
    }

    private Artist 가수_생성() {
        Artist artist = IU.getArtist();
        em.persistAndFlush(artist);
        return artist;
    }

    private User 유저_생성() {
        User user = NORMAL_USER.getUser();
        em.persistAndFlush(user);
        return user;
    }
}