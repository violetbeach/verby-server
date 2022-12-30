package com.verby.apiserver.artist.presentation;

import com.verby.core.artist.command.application.CreateArtistRequest;
import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "ADMIN")
class ArtistAdminControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("관리자는 가수를 생성할 수 있다.")
    void createArtist() throws Exception {
        // given
        CreateArtistRequest createArtistRequest = new CreateArtistRequest("IU");

        // when
        ResultActions result = mockMvc.perform(post("/admin/artists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createArtistRequest)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(createArtistRequest.getName()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("관리자 - 아티스트 추가",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("가수명")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("가수명")
                )));
    }

}