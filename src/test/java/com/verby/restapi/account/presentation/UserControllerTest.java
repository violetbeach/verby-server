package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.ResetNicknameRequest;
import com.verby.restapi.common.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("member")
class UserControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("ResetNicknameRequest로 닉네임을 재설정할 수 있다.")
    void resetNickname() throws Exception {
        // given
        ResetNicknameRequest resetNicknameRequest = new ResetNicknameRequest("updatedNickname");

        // when
        ResultActions result = mockMvc.perform(put("/users/me/nickname")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resetNicknameRequest)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("유저 닉네임 재설정",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("병경할 닉네임")
                ))
        );
    }

    @Test
    @DisplayName("세션의 회원 정보를 조회할 수 있다.")
    void me() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/me"));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(document("유저 정보 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 일련번호"),
                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("닉네임").optional(),
                        fieldWithPath("bio").type(JsonFieldType.STRING).description("소개 글"),
                        fieldWithPath("profile_image").type(JsonFieldType.STRING).description("프로필 이미지").optional()
                ))
        );
    }

}

