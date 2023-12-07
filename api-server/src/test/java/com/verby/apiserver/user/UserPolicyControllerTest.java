package com.verby.apiserver.user;

import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserPolicyControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("사용 불가 ID 목록을 조회할 수 있다.")
    void findAllUnavailableIds() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/unavailable-ids"));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(MockMvcRestDocumentation.document("사용 불가 ID 목록 조회",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("일련번호"),
                        fieldWithPath("[].login_id").type(JsonFieldType.STRING).description("사용 불가 로그인 ID")
                )
        ));
    }

}