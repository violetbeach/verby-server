package com.verby.restapi.cover.presentation;

import com.verby.restapi.support.presentation.BaseControllerTest;
import com.verby.restapi.support.storage.S3TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(S3TestConfig.class)
@WithUserDetails("member")
class CoverStorageControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("커버 전체 영상을 업로드할 수 있다.")
    void uploadVideo() throws Exception {
        MockMultipartFile video = new MockMultipartFile("video", "cover.mp4", "video/mp4", "Video Binary Data".getBytes());

        // when
        ResultActions result = mockMvc.perform(multipart("/covers/videos")
                .file(video));

        // then
        result.andExpect(status().isAccepted());

        // docs
        result.andDo(document("커버 영상 업로드",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParts(
                        partWithName("video").description("전체 영상 경로")
                ))
        );
    }

    @Test
    @DisplayName("커버 하이라이트 영상을 업로드할 수 있다.")
    void uploadHighlight() throws Exception {
        MockMultipartFile highlight = new MockMultipartFile("highlight", "highlight.mp4", "video/mp4", "Highlight Binary Data".getBytes());

        // when
        ResultActions result = mockMvc.perform(multipart("/covers/highlights")
                .file(highlight));

        // then
        result.andExpect(status().isAccepted());

        // docs
        result.andDo(document("커버 하이라이트 영상 업로드",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParts(
                        partWithName("highlight").description("하이라이트 영상 경로")
                ))
        );
    }

    @Test
    @DisplayName("커버 이미지 영상을 업로드할 수 있다.")
    void uploadImage() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "thumbnail.png", "image/png", "Image Binary Data".getBytes());

        // when
        ResultActions result = mockMvc.perform(multipart("/covers/images")
                .file(image));

        // then
        result.andExpect(status().isAccepted());

        // docs
        result.andDo(document("커버 썸네일 이미지 업로드",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParts(
                        partWithName("image").description("썸네일 이미지 경로")
                ))
        );
    }

}