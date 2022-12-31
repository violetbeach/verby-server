package com.verby.core.cover.query.dao;

import com.verby.core.artist.command.domain.Artist;
import com.verby.core.config.QueryDslConfig;
import com.verby.core.contest.command.domain.Contest;
import com.verby.core.cover.Cover;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.song.command.domain.Song;
import fixture.ContestFixture;
import com.verby.core.user.command.domain.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static fixture.ArtistFixture.IU;
import static fixture.CoverFixture.NORMAL_COVER;
import static fixture.SongFixture.좋은_날;
import static fixture.UserFixture.NORMAL_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import({QueryDslConfig.class, CoverQueryDaoTest.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("CoverSummaryQueryDao의")
class CoverQueryDaoTest {

    @Autowired
    private CoverQueryDao coverQueryDao;

    @Autowired
    private TestEntityManager em;

    @Disabled("조회 모델 DB 추가 예정에 따라 보류 (현재 Redis를 활용한 조회)")
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
            CoverQueryModel coverQueryModel = coverQueryDao.findById(cover.getId()).get();

            // then
            assertAll(
                    () -> assertThat(coverQueryModel.getTitle()).isEqualTo(cover.getTitle()),
                    () -> assertThat(coverQueryModel.getVideo()).isEqualTo(cover.getVideo()),
                    () -> assertThat(coverQueryModel.getHighlight()).isEqualTo(cover.getHighlight()),
                    () -> assertThat(coverQueryModel.getImage()).isEqualTo(cover.getImage()),

                    () -> assertThat(coverQueryModel.getPublisherId()).isEqualTo(user.getId()),
                    () -> assertThat(coverQueryModel.getPublisherName()).isEqualTo(user.getLoginId()),

                    () -> assertThat(coverQueryModel.getContestId()).isEqualTo(cover.getContestId()),

                    () -> assertThat(coverQueryModel.getArtistId()).isEqualTo(artist.getId()),
                    () -> assertThat(coverQueryModel.getArtistName()).isEqualTo(artist.getName()),

                    () -> assertThat(coverQueryModel.getSongId()).isEqualTo(song.getId()),
                    () -> assertThat(coverQueryModel.getSongName()).isEqualTo(song.getName())
            );
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


}