package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.VerificationToken;
import com.verby.restapi.user.command.domain.VerificationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserAuthService의")
class UserAuthServiceTest {
    @InjectMocks
    private UserAuthService userAuthService;
    @Mock
    VerificationTokenRepository verificationTokenRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("resetPassword 메서드는")
    class resetPassword {

        @Test
        @DisplayName("token과 ResetPasswordRequest를 가지고 해당 token의 회원의 비밀번호를 재설정한다.")
        void success() {
            // given
            ResetPasswordRequest request = new ResetPasswordRequest("token", "new_password1234");
            User user = new User("violetBeach13", "password13", "honey", "01012345678", null, false);

            given(passwordEncoder.encode(anyString())).willReturn(request.getNewPassword());
            given(verificationTokenRepository.findByKeyAndType(anyString(), eq(VerificationType.SET_PASSWORD)))
                    .willReturn(Optional.of(new VerificationToken(VerificationType.SET_PASSWORD, user)));

            // when
            userAuthService.resetPassword(request);

            // then
            assertThat(user.getPassword()).isEqualTo(request.getNewPassword());
        }

    }

}