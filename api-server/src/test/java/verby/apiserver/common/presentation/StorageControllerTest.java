package verby.apiserver.common.presentation;


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
import verby.apiserver.common.storage.dto.CreateUploadUrlRequest;
import verby.apiserver.common.storage.dto.Domain;
import verby.apiserver.common.storage.dto.Resource;
import verby.apiserver.support.presentation.BaseControllerTest;
import verby.apiserver.support.storage.S3TestConfig;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentResponse;

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
        CreateUploadUrlRequest request = new CreateUploadUrlRequest(Domain.COVER.toString(), Resource.IMAGE.toString());

        // when
        ResultActions result = mockMvc.perform(post("/upload-urls")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated());

        // docs
        result.andDo(document("업로드 Url 발급",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("domain_type").type(JsonFieldType.STRING).description("도메인 타입(COVER, IMAGE)"),
                        fieldWithPath("resource_type").type(JsonFieldType.STRING).description("리소스 타입(FULL_VIDEO, HIGHLIGHT, IMAGE)")
                )));
    }

}