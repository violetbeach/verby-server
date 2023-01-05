package com.verby.core.artist.command.domain;

import com.verby.core.support.repository.BaseRepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

class ArtistRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private TestEntityManager em;

    @Test
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