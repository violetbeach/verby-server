package com.verby.restapi.cover.presentation;

import com.verby.restapi.artist.command.domain.Artist;
import com.verby.restapi.contest.command.domain.Contest;
import com.verby.restapi.cover.command.application.PostCoverRequest;
import com.verby.restapi.cover.command.domain.Cover;
import com.verby.restapi.song.command.domain.Song;
import com.verby.restapi.support.fixture.domain.ContestFixture;
import com.verby.restapi.support.presentation.BaseControllerTest;
import com.verby.restapi.support.storage.S3TestConfig;
import com.verby.restapi.user.command.domain.User;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityManager;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static com.verby.restapi.support.fixture.domain.ArtistFixture.IU;
import static com.verby.restapi.support.fixture.domain.CoverFixture.NORMAL_COVER;
import static com.verby.restapi.support.fixture.domain.SongFixture.좋은_날;
import static com.verby.restapi.support.fixture.domain.UserFixture.NORMAL_USER;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(S3TestConfig.class)
@WithUserDetails("member")
class CoverControllerTest extends BaseControllerTest {

    @Autowired
    private S3Mock s3Mock;

    @Autowired
    private EntityManager em;

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

    @Test
    @DisplayName("CoverSummary를 전체 조회할 수 있다.")
    void findAll() throws Exception {
        // given
        User user = 유저_생성();
        Artist artist = 가수_생성();
        Song song = 곡_생성(artist);
        Contest contest = 선정곡_생성(song);

        커버_영상_생성(user, contest);
        커버_영상_생성(user, contest);
        커버_영상_생성(user, contest);

        // when
        ResultActions result = mockMvc.perform(get("/covers")
                .param("coverIdLt", "10")
                .param("pageSize", "10"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        result.andDo(document("커버 전체 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("coverIdLt").description("커버 id 조건 (lt)"),
                        parameterWithName("pageSize").description("페이지 사이즈")
                ),
                responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("[].contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("[].content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("[].video").type(JsonFieldType.STRING).description("풀 영상 url"),
                        fieldWithPath("[].highlight").type(JsonFieldType.STRING).description("하이라이트 url"),
                        fieldWithPath("[].image").type(JsonFieldType.STRING).description("썸네일 이미지 url"),
                        fieldWithPath("[].publisher_id").type(JsonFieldType.NUMBER).description("유저 일련번호"),
                        fieldWithPath("[].publisher_name").type(JsonFieldType.STRING).description("유저 닉네임"),
                        fieldWithPath("[].artist_id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("[].artist_name").type(JsonFieldType.STRING).description("가수 이름"),
                        fieldWithPath("[].song_id").type(JsonFieldType.NUMBER).description("곡 일련번호"),
                        fieldWithPath("[].song_name").type(JsonFieldType.STRING).description("곡 이름")
                )
        ));
    }

    @Test
    @DisplayName("id로 CoverSummary를 조회할 수 있다.")
    void findById() throws Exception {
        // given
        User user = 유저_생성();
        Artist artist = 가수_생성();
        Song song = 곡_생성(artist);
        Contest contest = 선정곡_생성(song);
        Cover cover = 커버_영상_생성(user, contest);

        // when
        ResultActions result = mockMvc.perform(get("/covers/{id}", cover.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(cover.getTitle()));

        result.andDo(document("커버 단일 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("id").description("커버 일련번호")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("video").type(JsonFieldType.STRING).description("풀 영상 url"),
                        fieldWithPath("highlight").type(JsonFieldType.STRING).description("하이라이트 url"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("썸네일 이미지 url"),
                        fieldWithPath("publisher_id").type(JsonFieldType.NUMBER).description("유저 일련번호"),
                        fieldWithPath("publisher_name").type(JsonFieldType.STRING).description("유저 닉네임"),
                        fieldWithPath("artist_id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("artist_name").type(JsonFieldType.STRING).description("가수 이름"),
                        fieldWithPath("song_id").type(JsonFieldType.NUMBER).description("곡 일련번호"),
                        fieldWithPath("song_name").type(JsonFieldType.STRING).description("곡 이름")
                )
        ));
    }

    @Test
    @DisplayName("PostCoverRequest, video, highlight, image로 Cover를 등록할 수 있다.")
    void postCover() throws Exception {
        // given
        PostCoverRequest request = new PostCoverRequest(
                1L,
                "title",
                "content"
        );
        MockMultipartFile requestJson = new MockMultipartFile("request", "test", "application/json", objectMapper.writeValueAsBytes(request));
        MockMultipartFile video = new MockMultipartFile("video", "cover.mp4", "video/mp4",  "Video Binary Data".getBytes());
        MockMultipartFile highlight = new MockMultipartFile("highlight", "highlight.mp4", "video/mp4", "Video Binary Data".getBytes());
        MockMultipartFile thumbnail = new MockMultipartFile("image", "thumbnail.png", "image/png", "Image Binary Data".getBytes());

        // when
        ResultActions result = mockMvc.perform(multipart("/covers")
                .file(video)
                .file(highlight)
                .file(thumbnail)
                .file(requestJson));

        // then
        result.andExpect(status().isAccepted())
                .andExpect(jsonPath("title").value(request.getTitle()));

        result.andDo(document("커버 등록",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParts(
                        partWithName("request").description("커버 정보 ({ contest_id: number, title: string, content: string })"),
                        partWithName("video").description("비디오 경로"),
                        partWithName("highlight").description("하이라이트 경로"),
                        partWithName("image").description("썸네일 이미지 경로")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("video").type(JsonFieldType.STRING).description("풀 영상 url"),
                        fieldWithPath("highlight").type(JsonFieldType.STRING).description("하이라이트 url"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("썸네일 이미지 url"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )
        ));
    }

    private Cover 커버_영상_생성(User user, Contest contest) {
        Cover cover = NORMAL_COVER.getCover(contest.getId(), user.getId());
        em.persist(cover);
        return cover;
    }

    private Contest 선정곡_생성(Song song) {
        Contest contest = ContestFixture.선정곡_IU_좋은날.getContest(song.getId());
        em.persist(contest);
        return contest;
    }

    private Song 곡_생성(Artist artist) {
        Song song = 좋은_날.getSong(artist.getId());
        em.persist(song);
        return song;
    }

    private Artist 가수_생성() {
        Artist artist = IU.getArtist();
        em.persist(artist);
        return artist;
    }

    private User 유저_생성() {
        User user = NORMAL_USER.getUser();
        em.persist(user);
        return user;
    }

}