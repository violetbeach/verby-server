package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.ResetNicknameRequest;
import com.verby.restapi.common.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("ResetNicknameRequest로 닉네임을 재설정할 수 있다.")
    void resetNickname() throws Exception {
        // given
        ResetNicknameRequest resetNicknameRequest = new ResetNicknameRequest("updatedNickname");

        // when
        ResultActions result = mockMvc.perform(put("/users/me/nickname")
                .session(memberSession)
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

}

