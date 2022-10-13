package com.verby.restapi.user.presentation;

import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserPolicyControllerTest extends BaseControllerTest {

    @Test
    void findAllUnavailableIds() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/unavailable-ids"));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(document("사용 불가 ID 목록 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("일련번호"),
                        fieldWithPath("[].login_id").type(JsonFieldType.STRING).description("사용 불가 로그인 ID")
                )
        ));
    }

}