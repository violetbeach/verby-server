package com.verby.restapi.cover.presentation;

import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.support.fixture.domain.CoverFixture;
import com.verby.restapi.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "MEMBER")
class CoverHitControllerTest extends BaseControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("coverId와 clinetIP로 커버 조회 수 등록을 할 수 있다.")
    void hit() throws Exception {
        // given
        Cover cover = CoverFixture.NORMAL_COVER.getCover(1L, 149029L);
        em.persist(cover);

        // when
        ResultActions result = mockMvc.perform(post("/covers/{coverId}/hits", cover.getId())
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isAccepted());

        // docs
        result.andDo(document("커버 조회수 등록",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("coverId").description("커버 일련번호")
                )
        ));
    }

}