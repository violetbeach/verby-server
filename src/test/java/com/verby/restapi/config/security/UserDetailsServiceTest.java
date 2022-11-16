
package com.verby.restapi.config.security;

import com.verby.restapi.user.command.application.SignUpRequest;
import com.verby.restapi.user.command.application.UserAuthService;
import com.verby.restapi.user.command.application.VerificationTokenRepository;
import com.verby.restapi.user.command.domain.VerificationToken;
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
    UserAuthService userAuthService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    VerificationTokenRepository tokenRepository;

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
        String phone = "01012345030";

        VerificationToken verificationToken = new VerificationToken(phone);
        tokenRepository.save(verificationToken);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId(loginId)
                .password(password)
                .name("testName")
                .phone(phone)
                .allowToMarketingNotification(false)
                .token(verificationToken.getKey())
                .build();
        userAuthService.signUp(signUpRequest);
    }

}