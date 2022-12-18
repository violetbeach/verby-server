package com.verby.restapi.cover.presentation;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.cover.command.domain.CoverLike;
import com.verby.restapi.support.fixture.domain.CoverFixture;
import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("member")
class CoverLikeControllerTest extends BaseControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("회원 정보와 coverId로 커버 좋아요를 할 수 있다.")
    void like() throws Exception {
        // given
        Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 149029L);
        em.persist(cover);

        // when
        ResultActions result = mockMvc.perform(post("/covers/{coverId}/likes", cover.getId())
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("커버 좋아요",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("coverId").description("커버 일련번호")
                )
        ));
    }

    @Test
    @DisplayName("회원 정보와 coverId로 커버 좋아요를 취소할 수 있다.")
    void unlike() throws Exception {
        // given
        Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 149029L);
        em.persist(cover);

        CoverLike like = new CoverLike(memberId, cover.getId());
        em.persist(like);

        // when
        ResultActions result = mockMvc.perform(delete("/covers/{coverId}/likes", cover.getId())
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("커버 좋아요 취소",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("coverId").description("커버 일련번호")
                )
        ));
    }

}