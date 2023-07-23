package com.verby.core.song.command.domain;

import com.verby.core.support.repository.MainRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class SongRepositoryTest extends MainRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
	@DisplayName("Song을 저장할 수 있다.")
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