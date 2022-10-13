package com.verby.restapi.common.error.presentation;

import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ErrorResponseDocumentationTest extends BaseControllerTest {

    @Test
    @DisplayName("에러 응답 Documentation")
    void errorResponseDocumentation() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/me"));

        // then
        result.andExpect(status().isUnauthorized());

        // docs
        result.andDo(document("에러 응답",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("code").type(JsonFieldType.STRING).description("에러 코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메시지")
                ))
        );
    }

}