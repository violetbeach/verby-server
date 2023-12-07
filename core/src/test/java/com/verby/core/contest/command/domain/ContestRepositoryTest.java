package com.verby.core.contest.command.domain;

import com.verby.core.support.repository.MainRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ContestRepositoryTest extends MainRepositoryTest {
    @Autowired
    private ContestRepository contestRepository;

    @Test
    @DisplayName("Contest를 저장할 수 있다.")
    void save() {
        // given
        Contest contest = new Contest(1L,
                "content",
                1,
                LocalDateTime.of(2022, 8, 1, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));


        // when
        contestRepository.save(contest);
        em.flush();

        // then
        Contest result = em.find(Contest.class, contest.getId());
        assertThat(result).isNotNull();
    }
}