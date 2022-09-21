package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.SignUpRequest;
import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.AccountRepository;
import com.verby.restapi.account.command.domain.RoleRepository;
import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static com.verby.restapi.support.fixture.domain.AccountFixture.NORMAL_ACCOUNT;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends BaseControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @DisplayName("유저를 생성할 수 있다.")
    void signup() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test123")
                .password("password12@")
                .name("TestName")
                .birthday(LocalDate.of(1994, 2, 10))
                .phone("01012345678")
                .allowToMarketingNotification(true)
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("login_id").value(signUpRequest.getLoginId()));

        // docs
        result.andDo(document("계정 등록",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("계정 비밀번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                        fieldWithPath("birthday").type(JsonFieldType.STRING).description("생년월일").optional(),
                        fieldWithPath("allow_to_marketing_notification").type(JsonFieldType.BOOLEAN).description("마케팅 정보 수신 동의 여부")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID")
                )));
    }

    @Test
    @DisplayName("SignupRequest에 loginId가 비어있으면 400(INVALID_INPUT_VALUE)을 반환한다.")
    void signup_fail_emptyLoginId() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("")
                .password("password12@")
                .name("TestName")
                .birthday(LocalDate.of(1994, 2, 10))
                .phone("01012345678")
                .allowToMarketingNotification(true)
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value(ErrorCode.INVALID_INPUT_VALUE.getCode()));
    }

    @Test
    @DisplayName("휴대전화 번호로 로그인 ID를 조회할 수 있다.")
    void findByLoginId() throws Exception {
        // given
        String loginId = "VioletBeach1";
        String phone = "01012345678";
        Account account = generateAccount(loginId, phone);

        // when
        ResultActions result = mockMvc.perform(get("/accounts/login-id")
                .param("phone", phone));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("id").value(account.getId()))
                .andExpect(jsonPath("login_id").value(loginId));

        // docs
        result.andDo(document("아이디 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("phone").description("계정 휴대전화 번호")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID")
                )));
    }

    @Test
    @DisplayName("로그인 아이디의 중복 여부를 확인할 수 있다.")
    void existsLoginId() throws Exception {
        // given
        String loginId = "VioletBeach1";
        generateAccount(loginId);

        // when
        ResultActions result = mockMvc.perform(head("/accounts")
                .param("login-id", loginId));

        // then
        result.andExpect(status().isOk())
                .andDo(document("아이디 중복 확인",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("login-id").description("계정 로그인 아이디")
                        )));
    }

    void generateAccount(String loginId) {
        accountRepository.save(NORMAL_ACCOUNT.getAccount(loginId));
    }

    Account generateAccount(String loginId, String phone) {
        return accountRepository.save(NORMAL_ACCOUNT.getAccount(loginId, phone));
    }

}