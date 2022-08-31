package com.verby.restapi.song.command.domain;

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
    private SongRepository songRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        // given
        Song song = new Song(1L, "사랑했지만", "imagePath");

        // when
        songRepository.save(song);

        // then
        SqlRowSet rsArtist = jdbcTemplate.queryForRowSet(
                "select * from song where id = ?",
                song.getId());
        assertThat(rsArtist.next()).isTrue();
    }
}