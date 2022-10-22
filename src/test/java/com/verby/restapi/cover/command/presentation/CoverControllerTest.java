package com.verby.restapi.cover.command.presentation;

import com.verby.restapi.cover.command.application.PostCoverRequest;
import com.verby.restapi.support.presentation.BaseControllerTest;
import com.verby.restapi.support.storage.S3TestConfig;
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

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(S3TestConfig.class)
@WithUserDetails("member")
class CoverControllerTest extends BaseControllerTest {

    @Autowired
    private S3Mock s3Mock;

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

    @Test
    @DisplayName("PostCoverRequest, video, highlight, image로 Cover를 등록할 수 있다.")
    void postCover() throws Exception {
        // given
        PostCoverRequest request = new PostCoverRequest(
                1L,
                "title"
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
                        partWithName("request").description("커버 정보 ({ contest_id: number, title: string })"),
                        partWithName("video").description("비디오 경로"),
                        partWithName("highlight").description("하이라이트 경로"),
                        partWithName("image").description("썸네일 이미지 경로")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("커버 일련번호"),
                        fieldWithPath("contest_id").type(JsonFieldType.NUMBER).description("선정곡 일련번호"),
                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                        fieldWithPath("video").type(JsonFieldType.STRING).description("풀 영상 url"),
                        fieldWithPath("highlight").type(JsonFieldType.STRING).description("하이라이트 url"),
                        fieldWithPath("image").type(JsonFieldType.STRING).description("썸네일 이미지 url"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )
        ));
    }

}