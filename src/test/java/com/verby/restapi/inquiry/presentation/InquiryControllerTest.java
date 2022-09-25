package com.verby.restapi.inquiry.presentation;

import com.verby.restapi.inquiry.command.application.InquiryRequest;
import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("member")
class InquiryControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("유저는 1:1 문의를 생성할 수 있다.")
    void create() throws Exception {
        // given
        InquiryRequest request = new InquiryRequest("커버 영상 업로드 관련 문의입니다.", "커버 영상을 어떻게 올리는 지 모르겠어요.");

        // when
        ResultActions result = mockMvc.perform(post("/inquiries")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
         result.andExpect(status().isCreated());

        // docs
        result.andDo(document("문의하기",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("title").type(JsonFieldType.STRING).description("문의 제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("문의 내용")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("문의 일련번호"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("문의 제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("문의 내용"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("문의 생성일자")
                )));
    }

}