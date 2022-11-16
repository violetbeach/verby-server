
package com.verby.restapi.config.security;

import com.verby.restapi.account.command.application.AccountService;
import com.verby.restapi.account.command.application.SignUpRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class UserDetailsServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByUsername() {
        // Given
        String id = "honey13";
        String password = "violetBeach1";
        generateAccount(id, password);

        // When
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);

        // Then
        assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();
    }

    @Test
    public void findByUsernameFail() {
        // Given
        String id = "noexists123";

        // When + Then
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(id);
        });

        assertTrue(exception.getMessage().contains(id));

    }

    void generateAccount(String loginId, String password) {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId(loginId)
                .password(password)
                .name("testName")
                .allowToMarketingNotification(false)
                .build();
        accountService.signUp(signUpRequest);
    }

}