package com.verby.core.contest.command.domain;


import com.verby.core.contest.exception.InvalidContestDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ContestTest {

    @Test
    @DisplayName("선정곡 생성자 테스트")
    public void Contest() {
        // Given & When
        Contest contest = new Contest(1L,
                "text",
                1,
                LocalDateTime.of(2022, 8, 1, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));

        // Then
        assertThat(contest).isNotNull();
    }

    @Test
    @DisplayName("선정곡 시작 시간이 종료 시간보다 늦으면 예외가 발생한다.")
    public void setContestDate() {
        // Then
        Assertions.assertThrows(InvalidContestDateException.class, () -> {
            // Given & WHen
            new Contest(1L,
                    "text",
                    1,
                    LocalDateTime.of(2022, 8, 2, 0, 0),
                    LocalDateTime.of(2022, 8, 1, 0, 0));
        });
    }

}