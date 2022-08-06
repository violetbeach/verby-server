package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.SignUpRequest;
import com.verby.restapi.common.presentation.BasicControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends BasicControllerTest {

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
                .andExpect(jsonPath("login_id").value(signUpRequest.getLoginId()))
                .andDo(document("계정 등록",
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

}