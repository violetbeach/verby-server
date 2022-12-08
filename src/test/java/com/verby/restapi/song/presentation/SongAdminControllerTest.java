package com.verby.restapi.song.presentation;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.artist.command.domain.ArtistRepository;
import com.verby.restapi.song.command.application.CreateSongRequest;
import com.verby.restapi.support.presentation.BaseControllerTest;
import com.verby.restapi.support.storage.S3TestConfig;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(S3TestConfig.class)
@WithMockUser(roles = "ADMIN")
class SongAdminControllerTest extends BaseControllerTest {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    private S3Mock s3Mock;

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

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
        result.andDo(document("관리자 - 곡 추가",
                getDocumentRequest(),
                getDocumentResponse(),
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