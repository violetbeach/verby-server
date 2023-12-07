package com.verby.core.artist.command.domain;

import com.verby.core.support.repository.MainRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ArtistRepositoryTest extends MainRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    @DisplayName("Artist를 저장할 수 있다.")
    void save() {
        // given
        Artist artist = new Artist("IU");

        // when
        artistRepository.save(artist);
        em.flush();

        // then
        Artist result = em.find(Artist.class, artist.getId());
        assertThat(result).isNotNull();
    }
}