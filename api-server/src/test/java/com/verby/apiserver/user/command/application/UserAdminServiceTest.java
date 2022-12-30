package com.verby.apiserver.user.command.application;


import com.verby.core.user.command.application.CreateAdminRequest;
import com.verby.core.user.command.application.CreatedUserInfo;
import com.verby.core.user.command.application.UserAdminService;
import com.verby.core.user.command.domain.RoleRepository;
import com.verby.core.user.command.domain.UserRepository;
import com.verby.core.user.exception.LoginIdDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserAdminService의")
class UserAdminServiceTest {
    @InjectMocks
    private UserAdminService userAdminService;
    @Mock
    UserRepository userRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("createAdmin 메서드는")
    class createAdmin {
        
        @Test
        @DisplayName("CreateAdminRequest를 가지고 관리자 회원을 생성한다.")
        void ItReturnCreatedUserInfo() {
            // given
            CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                    .loginId("violet13")
                    .password("password1234")
                    .name("nickname")
                    .build();

            // when
            CreatedUserInfo userInfo = userAdminService.createAdmin(createAdminRequest);

            // then
            assertNotNull(userInfo);
            assertEquals(createAdminRequest.getLoginId(), userInfo.getLoginId());
        }

        @Test
        @DisplayName("로그인 ID가 이미 존재하면 LoginIdDuplicateException를 발생한다.")
        void withDuplicatedLoginId_ItThrowLoginIdDeuplicateException() {
            // given
            CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                    .loginId("violet13")
                    .password("password1234")
                    .name("nickname")
                    .build();
            given(userRepository.existsByLoginId(createAdminRequest.getLoginId()))
                    .willReturn(true);

            // when & then
            assertThatThrownBy(() -> userAdminService.createAdmin(createAdminRequest))
                    .isInstanceOf(LoginIdDuplicateException.class);
        }

    }

}