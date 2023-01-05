package com.verby.core.song.command.domain;

import com.verby.core.support.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class SongRepositoryTest extends BaseRepositoryTest {

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