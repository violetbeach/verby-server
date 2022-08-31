package com.verby.restapi.artist.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        // given
        Artist artist = new Artist("IU");

        // when
        artistRepository.save(artist);

        // then
        SqlRowSet rsArtist = jdbcTemplate.queryForRowSet(
                "select * from artist where id = ?",
                artist.getId());
        assertThat(rsArtist.next()).isTrue();
    }
}