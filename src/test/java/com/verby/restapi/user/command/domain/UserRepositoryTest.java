package com.verby.restapi.user.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.verby.restapi.support.fixture.domain.UserFixture.NORMAL_USER;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    void findByLoginId() {
        // given
        User newUser = NORMAL_USER.getUser();
        em.persistAndFlush(newUser);

        // when
        Optional<User> user = userRepository.findByLoginId(newUser.getLoginId());

        // then
        assertThat(user).isPresent();
    }

    @Test
    void save() {
        // given
        String loginId = "violetbeach13";
        User user = new User(loginId,
                "password1234",
                "nickname12",
                "01012345678",
                null,
                false
                );

        // when
        userRepository.save(user);
        em.flush();

        // then
        User result = em.find(User.class, user.getId());
        assertThat(result).isNotNull();
    }
}