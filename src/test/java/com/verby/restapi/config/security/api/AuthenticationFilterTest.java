package com.verby.restapi.config.security.api;

import com.verby.restapi.account.command.domain.AccountRepository;
import com.verby.restapi.account.command.domain.RoleRepository;
import com.verby.restapi.account.command.domain.UserRepository;
import com.verby.restapi.config.security.AuthenticationRequest;
import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static com.verby.restapi.support.fixture.domain.AccountFixture.NORMAL_ACCOUNT;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthenticationFilterTest extends BaseControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("유저 정보를 이용해 세션을 생성할 수 있다.")
    void attemptAuthentication() throws Exception {
        // given
        String loginId = "test1234";
        String password = "test5678";
        generateAccount(loginId, password);
        AuthenticationRequest request = new AuthenticationRequest(loginId, password);

        // when
        ResultActions result = mockMvc.perform(post("/accounts/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("세션 등록(로그인)",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("계정 비밀번호")
                )
        ));
    }

    void generateAccount(String loginId, String password) {
        accountRepository.save(NORMAL_ACCOUNT.getAccount(loginId, password));
    }

}