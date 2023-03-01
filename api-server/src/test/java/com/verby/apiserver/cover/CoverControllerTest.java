package com.verby.apiserver.cover;


import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.artist.command.domain.Artist;
import com.verby.core.contest.command.domain.Contest;
import com.verby.core.cover.command.application.PostCoverRequest;
import com.verby.core.cover.command.domain.Cover;
import com.verby.core.cover.query.dto.CoverQueryModel;
import com.verby.core.song.command.domain.Song;
import com.verby.core.user.command.domain.User;
import fixture.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails("member")
class CoverControllerTest extends BaseControllerTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    TestCoverQueryModelRepository queryModelRepository;

    @Test
    @DisplayName("CoverSummary를 전체 조회할 수 있다.")
    void findAll() throws Exception {
        // given
        User user = 유저_생성();
        Artist artist = 가수_생성();
        Song song = 곡_생성(artist);
        Contest contest = 선정곡_생성(song);
        Cover cover = 커버_영상_생성(user, contest);

        커버_조회_모델_생성(cover);

        // when
        ResultActions result = mockMvc.perform(get("/covers")
                .param("coverIdLt", String.valueOf(cover.getId() + 1))
                .param("contestId", contest.getId().toString())
                .param("pageSize", "10"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(1)));

        result.andDo(MockMvcRestDocumentation.document("커버 전체 조회",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestParameters(
                        parameterWithName("coverIdLt").description("커버 일련번호 (lt)"),
                        parameterWithName("contestId").description("선정곡 일련번호 (eq)"),
                        parameterWithName("pageSize").description("페이지 사이즈")
                ),
                responseFields(
                        subsectionWithPath("data").type(JsonFieldType.ARRAY).description("커버 목록"),
                        fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("data[].contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("data[].title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("data[].content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("data[].video").type(JsonFieldType.STRING).description("풀 영상 url"),
                        fieldWithPath("data[].highlight").type(JsonFieldType.STRING).description("하이라이트 url"),
                        fieldWithPath("data[].image").type(JsonFieldType.STRING).description("썸네일 이미지 url"),
                        fieldWithPath("data[].publisher_id").type(JsonFieldType.NUMBER).description("유저 일련번호"),
                        fieldWithPath("data[].publisher_name").type(JsonFieldType.STRING).description("유저 닉네임"),
                        fieldWithPath("data[].artist_id").type(JsonFieldType.NUMBER).description("가수 일련번호"),
                        fieldWithPath("data[].artist_name").type(JsonFieldType.STRING).description("가수 이름"),
                        fieldWithPath("data[].song_id").type(JsonFieldType.NUMBER).description("곡 일련번호"),
                        fieldWithPath("data[].song_name").type(JsonFieldType.STRING).description("곡 이름"),
                        fieldWithPath("data[].like_count").type(JsonFieldType.NUMBER).description("좋아요 수"),
                        fieldWithPath("data[].hits").type(JsonFieldType.NUMBER).description("조회수"),

                        subsectionWithPath("next").type(JsonFieldType.OBJECT).description("조회 Meta-data"),
                        fieldWithPath("next.key").type(JsonFieldType.NUMBER).description("No Offset 키"),
                        fieldWithPath("next.size").type(JsonFieldType.NUMBER).description("검색 사이즈")
                )
        ));
    }

    @Nested
    @DisplayName("findById 메서드는")
    class findById {

        @Test
        @DisplayName("커버 조회 모델과 200 코드를 반환한다.")
        void ItReturn200() throws Exception {
            // given
            User user = 유저_생성();
            Artist artist = 가수_생성();
            Song song = 곡_생성(artist);
            Contest contest = 선정곡_생성(song);
            Cover cover = 커버_영상_생성(user, contest);

            커버_조회_모델_생성(cover);

            // when
            ResultActions result = mockMvc.perform(get("/covers/{id}", cover.getId()));

            // then
            result.andExpect(status().isOk())
                    .andExpect(jsonPath("title").value(cover.getTitle()));

            result.andDo(MockMvcRestDocumentation.document("커버 단일 조회",
                    ApiDocumentUtils.getDocumentRequest(),
                    ApiDocumentUtils.getDocumentResponse(),
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
                            fieldWithPath("song_name").type(JsonFieldType.STRING).description("곡 이름"),
                            fieldWithPath("like_count").type(JsonFieldType.NUMBER).description("좋아요 수"),
                            fieldWithPath("hits").type(JsonFieldType.NUMBER).description("조회수")
                    )
            ));

        }

    }

    @Test
    @DisplayName("PostCoverRequest로 Cover를 등록할 수 있다.")
    void create() throws Exception {
        // given
        Artist artist = 가수_생성();
        Song song = 곡_생성(artist);
        Contest contest = 선정곡_생성(song);

        PostCoverRequest request = new PostCoverRequest(
                contest.getId(),
                "커버 영상 제목입니다.",
                "커버 영상 설명",
                "/static/cover/video/sample.mp4",
                "/static/cover/highlight/sample.mp4",
                "/static/cover/image/sample.png"
        );

        // when
        ResultActions result = mockMvc.perform(post("/covers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(request.getTitle()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("커버 등록",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                        fieldWithPath("video").type(JsonFieldType.STRING).description("전체 영상 경로"),
                        fieldWithPath("highlight").type(JsonFieldType.STRING).description("하이라이트 경로"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("썸네일 이미지 경로")
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
        Cover cover = CoverFixture.NORMAL_COVER.getCover(contest.getId(), user.getId());
        em.persist(cover);
        return cover;
    }

    private Contest 선정곡_생성(Song song) {
        Contest contest = ContestFixture.선정곡_IU_좋은날.getContest(song.getId());
        em.persist(contest);
        return contest;
    }

    private Song 곡_생성(Artist artist) {
        Song song = SongFixture.좋은_날.getSong(artist.getId());
        em.persist(song);
        return song;
    }

    private Artist 가수_생성() {
        Artist artist = ArtistFixture.IU.getArtist();
        em.persist(artist);
        return artist;
    }

    private User 유저_생성() {
        User user = UserFixture.NORMAL_USER.getUser();
        em.persist(user);
        return user;
    }

    private void 커버_조회_모델_생성(Cover cover) {
        CoverQueryModel coverQueryModel = new CoverQueryModel(
                cover.getId(),
                cover.getContestId(),
                cover.getPublisherId(),
                "작성자 닉네임",
                cover.getTitle(),
                cover.getContent(),
                cover.getVideo(),
                cover.getHighlight(),
                cover.getImage(),
                1L,
                "가수 이름",
                1L,
                "곡 이름",
                0L,
                cover.getHits()
        );
        queryModelRepository.save(coverQueryModel);
    }

}