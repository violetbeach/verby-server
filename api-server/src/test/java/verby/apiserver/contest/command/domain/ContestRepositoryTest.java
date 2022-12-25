package verby.apiserver.contest.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContestRepositoryTest {

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private TestEntityManager em;

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
        em.flush();

        // then
        Contest result = em.find(Contest.class, contest.getId());
        assertThat(result).isNotNull();
    }
}