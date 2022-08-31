package com.verby.restapi.contest.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalDateTime;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContestRepositoryTest {

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void save() {
        // given
        Contest contest = new Contest(1L,
                "content",
                1,
                LocalDateTime.of(2022, 8, 1, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));


        // when
        contestRepository.save(contest);

        // then
        SqlRowSet rsAccount = jdbcTemplate.queryForRowSet(
                "select * from contest where id = ?",
                contest.getId());
    }
}