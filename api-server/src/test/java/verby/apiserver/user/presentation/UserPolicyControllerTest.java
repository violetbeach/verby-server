package verby.apiserver.user.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import verby.apiserver.support.presentation.BaseControllerTest;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentResponse;

class UserPolicyControllerTest extends BaseControllerTest {

    @Test
    void findAllUnavailableIds() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/unavailable-ids"));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(document("사용 불가 ID 목록 조회",
                getDocumentRequest(),
                getDocumentResponse(),
                responseFields(
                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("일련번호"),
                        fieldWithPath("[].login_id").type(JsonFieldType.STRING).description("사용 불가 로그인 ID")
                )
        ));
    }

}