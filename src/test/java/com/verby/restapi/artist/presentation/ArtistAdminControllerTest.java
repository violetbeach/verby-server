package com.verby.restapi.artist.presentation;

import com.verby.restapi.artist.command.application.CreateArtistRequest;
import com.verby.restapi.common.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ArtistAdminControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("관리자는 가수를 생성할 수 있다.")
    void createArtist() throws Exception {
        // given
        CreateArtistRequest createArtistRequest = new CreateArtistRequest("IU");

        // when
        ResultActions result = mockMvc.perform(post("/admin/artists")
                .session(adminSession)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createArtistRequest)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(createArtistRequest.getName()));

        // docs
        result.andDo(document("관리자 - 아티스트 추가",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("가수명")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("가수명")
                )));
    }

}