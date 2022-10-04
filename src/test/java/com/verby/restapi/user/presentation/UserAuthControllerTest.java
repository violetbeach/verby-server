package com.verby.restapi.user.presentation;

import com.verby.restapi.support.presentation.BaseControllerTest;
import com.verby.restapi.user.command.application.ResetPasswordRequest;
import com.verby.restapi.user.command.application.VerificationTokenRepository;
import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.UserRepository;
import com.verby.restapi.user.command.domain.VerificationToken;
import com.verby.restapi.user.command.domain.VerificationType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static com.verby.restapi.support.fixture.domain.UserFixture.NORMAL_USER;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserAuthControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationRepository;

    @Test
    @DisplayName("Verification Token으로 로그인 ID를 조회할 수 있다.")
    void findByLoginId() throws Exception {
        // given
        User user = generateUser();
        VerificationToken verificationToken = generateVerificationToken(VerificationType.FIND_ID, user);

        // when
        ResultActions result = mockMvc.perform(get("/users/login-id")
                .param("token", verificationToken.getKey()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("login_id").value(user.getLoginId()));

        // docs
        result.andDo(document("ID 찾기",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("token").description("Verification 토큰")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID")
                )));
    }

    @Test
    @DisplayName("비밀번호를 재설정할 수 있다.")
    void resetPassword() throws Exception {
        // given
        User user = generateUser();
        VerificationToken verificationToken = generateVerificationToken(VerificationType.SET_PASSWORD, user);

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("new_password1");

        // when
        ResultActions result = mockMvc.perform(put("/users/password")
                .param("token", verificationToken.getKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resetPasswordRequest)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("계정 비밀번호 재설정",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("token").description("인증 토큰 키")
                ),
                requestFields(
                        fieldWithPath("new_password").type(JsonFieldType.STRING).description("재설정할 비밀번호")
                )));
    }

    VerificationToken generateVerificationToken(VerificationType type, User user) {
        VerificationToken verificationToken = new VerificationToken(type, user);
        return verificationRepository.save(verificationToken);
    };

    User generateUser() {
        return userRepository.save(NORMAL_USER.getUser());
    }
}