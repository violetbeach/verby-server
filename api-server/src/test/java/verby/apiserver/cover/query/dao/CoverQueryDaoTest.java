package verby.apiserver.cover.query.dao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import verby.apiserver.artist.command.domain.Artist;
import verby.apiserver.config.QueryDslConfig;
import verby.apiserver.contest.command.domain.Contest;
import verby.apiserver.cover.command.domain.Cover;
import verby.apiserver.cover.query.dto.CoverQueryModel;
import verby.apiserver.song.command.domain.Song;
import verby.apiserver.support.fixture.domain.ContestFixture;
import verby.apiserver.user.command.domain.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static verby.apiserver.support.fixture.domain.ArtistFixture.IU;
import static verby.apiserver.support.fixture.domain.CoverFixture.NORMAL_COVER;
import static verby.apiserver.support.fixture.domain.SongFixture.좋은_날;
import static verby.apiserver.support.fixture.domain.UserFixture.NORMAL_USER;

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