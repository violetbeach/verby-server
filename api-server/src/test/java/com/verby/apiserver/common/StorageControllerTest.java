package com.verby.apiserver.common;


import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.storage.dto.CreateUploadUrlRequest;
import com.verby.core.storage.dto.Domain;
import com.verby.core.storage.dto.Resource;
import config.storage.S3TestConfig;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(S3TestConfig.class)
@WithMockUser(roles = "MEMBER")
class StorageControllerTest extends BaseControllerTest {

    @Autowired
    private S3Mock s3Mock;

    @AfterEach
    public void tearDown() {
        s3Mock.stop();
    }

    @Test
    @DisplayName("리소스를 업로드할 수 있는 url을 반환한다.")
    public void publishPreSignUrl() throws Exception {
        // given
        CreateUploadUrlRequest request = new CreateUploadUrlRequest(Domain.COVER.toString(), Resource.IMAGE.toString(), "test.png");

        // when
        ResultActions result = mockMvc.perform(post("/upload-urls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated());

        // docs
        result.andDo(MockMvcRestDocumentation.document("업로드 Url 발급",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("domain_type").type(JsonFieldType.STRING).description("도메인 타입(COVER, IMAGE)"),
                        fieldWithPath("resource_type").type(JsonFieldType.STRING).description("리소스 타입(FULL_VIDEO, HIGHLIGHT, IMAGE)"),
                        fieldWithPath("file_name").type(JsonFieldType.STRING).description("파일 명")
                )));
    }

}