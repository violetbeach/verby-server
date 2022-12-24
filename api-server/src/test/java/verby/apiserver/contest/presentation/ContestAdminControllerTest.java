package verby.apiserver.contest.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;
import verby.apiserver.artist.command.domain.Artist;
import verby.apiserver.artist.command.domain.ArtistRepository;
import verby.apiserver.contest.command.application.CreateContestRequest;
import verby.apiserver.song.command.domain.Song;
import verby.apiserver.song.command.domain.SongRepository;
import verby.apiserver.support.presentation.BaseControllerTest;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentResponse;

@WithMockUser(roles = "ADMIN")
class ContestAdminControllerTest extends BaseControllerTest {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    SongRepository songRepository;

    Song song;

    @BeforeEach
    void setup() {
        Artist artist = artistRepository.save(new Artist("IU"));
        song = songRepository.save(new Song(artist.getId(), "좋은 날", "test_url"));
    }

    @Test
    @DisplayName("관리자는 선정곡을 추가할 수 있다.")
    void createContest() throws Exception {
        // given
        CreateContestRequest request = new CreateContestRequest(
                song.getId(),
                "제 148회차 선정곡! 상금 100만원!",
                148,
                LocalDateTime.of(2022, 8, 1, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));

        // when
        ResultActions result = mockMvc.perform(post("/admin/contests")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated());

        // docs
        result.andDo(document("관리자 - 선정곡 추가",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("song_id").type(JsonFieldType.NUMBER).description("곡 ID"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("선정곡(대회) 내용"),
                        fieldWithPath("round").type(JsonFieldType.NUMBER).description("회차"),
                        fieldWithPath("start_time").type(JsonFieldType.STRING).description("시작 시간"),
                        fieldWithPath("end_time").type(JsonFieldType.STRING).description("종료 시간")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("song_id").type(JsonFieldType.NUMBER).description("곡 ID"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("선정곡(대회) 내용"),
                        fieldWithPath("round").type(JsonFieldType.NUMBER).description("회차"),
                        fieldWithPath("start_time").type(JsonFieldType.STRING).description("시작 시간"),
                        fieldWithPath("end_time").type(JsonFieldType.STRING).description("종료 시간"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )));
    }

}