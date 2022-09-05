package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.*;
import org.junit.jupiter.api.BeforeEach;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("AccountAdminService의")
class AccountAdminServiceTest {
    @InjectMocks
    private AccountAdminService accountAdminService;
    @Mock
    AccountRepository accountRepository;
    @Mock
    RoleRepository roleRepository;
    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("createAdmin 메서드는")
    class createAdmin {
        
        @Test
        @DisplayName("CreateAdminRequest를 가지고 관리자 회원을 생성한다.")
        void success() {
            // given
            CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                    .loginId("violet13")
                    .password("password1234")
                    .name("nickname")
                    .build();

            // when
            AccountInfo accountInfo = accountAdminService.createAdmin(createAdminRequest);

            // then
            assertNotNull(accountInfo);
            assertEquals(createAdminRequest.getLoginId(), accountInfo.getLoginId());
        }

        @Test
        @DisplayName("로그인 ID가 이미 존재하면 LoginIdDuplicateException를 발생한다.")
        void fail_duplicatedLoginId() {
            // given
            CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                    .loginId("violet13")
                    .password("password1234")
                    .name("nickname")
                    .build();
            given(accountRepository.existsByLoginId(createAdminRequest.getLoginId()))
                    .willReturn(true);

            // when & then
            assertThatThrownBy(() -> accountAdminService.createAdmin(createAdminRequest))
                    .isInstanceOf(LoginIdDuplicateException.class);
        }

    }

}