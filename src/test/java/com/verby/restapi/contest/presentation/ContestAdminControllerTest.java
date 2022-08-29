package com.verby.restapi.contest.presentation;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.artist.command.domain.ArtistRepository;
import com.verby.restapi.common.presentation.BaseControllerTest;
import com.verby.restapi.contest.command.application.CreateContestRequest;
import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.song.command.domain.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        CreateContestRequest createContestRequest = new CreateContestRequest(
                song.getId(),
                "제 148회차 선정곡! 상금 100만원!",
                148,
                LocalDateTime.of(2022, 8, 1, 0, 0),
                LocalDateTime.of(2022, 8, 2, 0, 0));

        // when
        ResultActions result = mockMvc.perform(post("/admin/contests")
                .session(adminSession)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createContestRequest)));

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
                        fieldWithPath("end_time").type(JsonFieldType.STRING).description("종료 시간")

                )));
    }

}