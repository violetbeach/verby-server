package com.verby.apiserver.user;


import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.user.command.application.CreateAdminRequest;
import com.verby.core.user.command.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "ADMIN")
class UserAdminControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("관리자 유저를 생성할 수 있다.")
    void createAdmin() throws Exception {
        // given
        CreateAdminRequest createAdminRequest = CreateAdminRequest.builder()
                .loginId("test123")
                .password("password12@")
                .name("TestName")
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/admin/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createAdminRequest)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("login_id").value(createAdminRequest.getLoginId()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("관리자 - 관리자 회원 생성",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("계정 비밀번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )));
    }

}