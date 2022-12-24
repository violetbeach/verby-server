package verby.apiserver.song.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void save() {
        // given
        Song song = new Song(1L, "사랑했지만", "imagePath");

        // when
        songRepository.save(song);
        em.flush();

        // then
        Song result = em.find(Song.class, song.getId());
        assertThat(result).isNotNull();
    }
}