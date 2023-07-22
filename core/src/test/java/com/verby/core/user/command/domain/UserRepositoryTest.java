package com.verby.core.user.command.domain;

import com.verby.core.support.repository.MainRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static fixture.UserFixture.NORMAL_USER;
import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends MainRepositoryTest {

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
        Assertions.assertThat(user).isPresent();
    }

    @Test
    void save() {
        // given
        String loginId = "violetbeach13";
        User user = new User(loginId,
                "password1234",
                "nickname12",
                "01012345678",
                Gender.MALE,
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