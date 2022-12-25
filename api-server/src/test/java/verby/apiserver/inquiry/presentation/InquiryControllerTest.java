package verby.apiserver.inquiry.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import verby.apiserver.inquiry.command.application.InquiryRequest;
import verby.apiserver.inquiry.command.domain.Inquiry;
import verby.apiserver.inquiry.command.domain.InquiryRepository;
import verby.apiserver.support.presentation.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static verby.apiserver.support.fixture.domain.InquiryFixture.NORMAL_INQUIRY;

@WithUserDetails("member")
class InquiryControllerTest extends BaseControllerTest {

    @Autowired
    InquiryRepository inquiryRepository;

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

    @Test
    @DisplayName("자신의 문의 내역을 모두 조회할 수 있다.")
    void findAll() throws Exception {
        // given
        Inquiry inquiry = generateInquiry(memberId);

        // when
        ResultActions result = mockMvc.perform(get("/inquiries"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(inquiry.getId()))
                .andExpect(jsonPath("$[0].inquirer_id").value(memberId))
                .andExpect(jsonPath("$[0].title").value(inquiry.getTitle()))
                .andExpect(jsonPath("$[0].content").value(inquiry.getContent()))
                .andExpect(jsonPath("$", hasSize(1)));

        // docs
        result.andDo(document("문의 내역 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("문의 일련번호"),
                        fieldWithPath("[].inquirer_id").type(JsonFieldType.NUMBER).description("문의자 ID"),
                        fieldWithPath("[].answerer_id").type(JsonFieldType.NUMBER).description("답변자 ID").optional(),
                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("문의 제목"),
                        fieldWithPath("[].content").type(JsonFieldType.STRING).description("문의 내용"),
                        fieldWithPath("[].answer").type(JsonFieldType.STRING).description("문의 답변").optional(),
                        fieldWithPath("[].status").type(JsonFieldType.STRING).description("문의 상태"),
                        fieldWithPath("[].created_at").type(JsonFieldType.STRING).description("문의 등록일자"),
                        fieldWithPath("[].answered_at").type(JsonFieldType.STRING).description("문의 답변일자").optional()
                )));
    }

    Inquiry generateInquiry(long inquirerId) {
        Inquiry inquiry = NORMAL_INQUIRY.getInquiry(inquirerId);
        return inquiryRepository.save(inquiry);
    }

}