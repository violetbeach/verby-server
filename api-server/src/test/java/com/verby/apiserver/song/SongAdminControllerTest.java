package com.verby.apiserver.song;


import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.artist.command.domain.Artist;
import com.verby.core.artist.command.domain.ArtistRepository;
import com.verby.core.song.command.application.CreateSongRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = "ADMIN")
class SongAdminControllerTest extends BaseControllerTest {

    @Autowired
    ArtistRepository artistRepository;

    @Test
    @DisplayName("관리자는 곡을 추가할 수 있다.")
    void createSong() throws Exception {
        // given
        Artist artist = generateArtist();
        CreateSongRequest request = new CreateSongRequest("사랑했지만", "/static/song/image.png");

        // when
        ResultActions result = mockMvc.perform(post("/admin/artists/{artistId}/songs", artist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("name").value(request.getName()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("관리자 - 곡 추가",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                pathParameters(
                        parameterWithName("artistId").description("가수 일련번호")
                ),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("곡 이름"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("이미지 경로")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("곡 일련번호"),
                        fieldWithPath("artist_id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("곡명"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("이미지 경로")
                )));
    }

    Artist generateArtist() {
        Artist artist = new Artist("IU");
        return artistRepository.save(artist);
    }

}