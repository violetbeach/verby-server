package com.verby.apiserver.user.command.application;

import com.verby.core.user.command.application.*;
import com.verby.core.user.command.domain.*;
import com.verby.core.user.exception.LoginIdDuplicateException;
import com.verby.core.user.exception.PhoneNumberDuplicateException;
import com.verby.core.user.exception.UnavailableLoginIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserAuthService의")
class UserAuthServiceTest {
    @InjectMocks
    private UserAuthService userAuthService;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    UnavailableIDRepository unavailableIDRepository;
    @Mock
    VerificationTokenRepository verificationTokenRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("resetPassword 메서드는")
    class resetPassword {

        @Test
        @DisplayName("ResetPasswordRequest를 가지고 해당 token의 회원의 비밀번호를 재설정한다.")
        void ItResetUserPassword() {
            // given
            ResetPasswordRequest request = new ResetPasswordRequest("token", "new_password1234");
            User user = new User("violetBeach13", "password13", "honey", "01012345678", Gender.MALE,null, false);

            given(passwordEncoder.encode(anyString())).willReturn(request.getNewPassword());
            given(verificationTokenRepository.findByKey(anyString()))
                    .willReturn(Optional.of(new VerificationToken(user.getPhone())));
            given(userRepository.findByPhone(user.getPhone()))
                    .willReturn(Optional.of(user));

            // when
            userAuthService.resetPassword(request);

            // then
            assertThat(user.getPassword()).isEqualTo(request.getNewPassword());
        }

    }

    @Nested
    @DisplayName("signup 메서드는")
    class signup {

        SignUpRequest request;

        @BeforeEach
        void setup() {
            request = new SignUpRequest("violetbeach12", "password1234", "Honey", null, "01043042900",  Gender.MALE, true, "token");
            given(verificationTokenRepository.findByKey(anyString()))
                    .willReturn(Optional.of(new VerificationToken(request.getPhone())));
        }

        @Test
        @DisplayName("생성할 회원 데이터를 가지고 회원을 생성하고, 생성한 회원을 반환한다.")
        void ItReturnCreatedUserInfo() {
            // given
            given(roleRepository.findByName(Role.MEMBER)).willReturn(new UserRole(Role.MEMBER));
            given(unavailableIDRepository.findAll()).willReturn(new ArrayList<>());

            // when
            CreatedUserInfo userInfo = userAuthService.signUp(request);

            // then
            assertThat(userInfo).isNotNull();
        }

        @Test
        @DisplayName("Request의 로그인 ID가 이미 존재하면 LoginIdDuplicateException을 발생시킨다.")
        void withExistsLoginId_ItThrowLoginIdDuplicateException() {
            // given;
            given(userRepository.existsByLoginId(request.getLoginId()))
                    .willReturn(true);

            // when & then
            assertThatThrownBy(() -> userAuthService.signUp(request))
                    .isInstanceOf(LoginIdDuplicateException.class);
        }

        @Test
        @DisplayName("Request의 휴대폰 번호가 이미 존재하면 PhoneNumberDuplicateException을 발생시킨다.")
        void withExistsPhone_ItThrowPhoneNumberDuplicateException() {
            // given;
            given(userRepository.existsByPhone(request.getPhone()))
                    .willReturn(true);

            // when & then
            assertThatThrownBy(() -> userAuthService.signUp(request))
                    .isInstanceOf(PhoneNumberDuplicateException.class);
        }

        @Test
        @DisplayName("Request의 Login id가 사용 불가능한 ID라면 UnAvailableLoginIdException을 발생시킨다.")
        void withUnavailableLoginId_ItThrowUnavailableLoginIdException() {
            // given;
            given(unavailableIDRepository.findAll())
                    .willReturn(List.of(new UnavailableID(request.getLoginId())));

            // when & then
            assertThatThrownBy(() -> userAuthService.signUp(request))
                    .isInstanceOf(UnavailableLoginIdException.class);
        }

    }


}