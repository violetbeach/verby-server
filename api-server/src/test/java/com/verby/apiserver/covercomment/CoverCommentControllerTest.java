package com.verby.apiserver.covercomment;

import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.covercomment.command.application.CommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import support.repository.TestCoverQueryModelRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static fixture.CoverQueryModelFixture.NORMAL_COVER_QUERY_MODEL;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("member")
class CoverCommentControllerTest extends BaseControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TestCoverQueryModelRepository queryModelRepository;

    @Test
    @DisplayName("CommentRequest로 CoverComment를 등록할 수 있다.")
    void create() throws Exception {
        // given
        CoverQueryModel cover = 커버_생성();

        CommentRequest request = new CommentRequest(
                "Comment 입니다.",
                null
        );

        // when
        ResultActions result = mockMvc.perform(post("/covers/{coverId}/comments", cover.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("content").value(request.getContent()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("커버 댓글 등록",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                pathParameters(
                        parameterWithName("coverId").description("Cover Id")
                ),
                requestFields(
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("reply_to").type(JsonFieldType.NUMBER).description("답글 대상 댓글 일련번호").optional()
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("cover_id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("commenter_id").type(JsonFieldType.NUMBER).description("댓글 작성자 일련번호"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("reply_to").type(JsonFieldType.NUMBER).description("답글 대상 댓글 일련번호").optional(),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )
        ));
    }

    private CoverQueryModel 커버_생성() {
        CoverQueryModel coverQueryModel = NORMAL_COVER_QUERY_MODEL.getCoverQueryModel(1L);
        return queryModelRepository.save(coverQueryModel);
    }

}