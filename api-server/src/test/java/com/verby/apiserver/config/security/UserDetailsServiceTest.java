
package com.verby.apiserver.config.security;


import com.verby.apiserver.user.command.application.SignUpRequest;
import com.verby.apiserver.user.command.application.UserAuthService;
import com.verby.apiserver.user.command.application.VerificationTokenRepository;
import com.verby.apiserver.user.command.domain.VerificationToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
@DisplayName("UserDetailsService의")
class UserDetailsServiceTest {

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Nested
    @DisplayName("findByUsername 메서드는")
    class findByUsername {
        @Test
        @DisplayName("UserDetails를 반환한다.")
        public void ItReturnUserDetails() {
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
        @DisplayName("해당 loginId가 존재하지 않으면 UsernameNotFoundException를 발생시킨다.")
        public void withNotExistsLoginId_ItThrowUsernameNotFoundException() {
            // Given
            String id = "noexists123";

            // When + Then
            assertThatThrownBy(() -> userDetailsService.loadUserByUsername(id))
                    .isInstanceOf(UsernameNotFoundException.class);
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

}