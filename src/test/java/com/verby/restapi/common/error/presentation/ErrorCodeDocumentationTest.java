package com.verby.restapi.common.error.presentation;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.presentation.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ErrorCodeDocumentationTest extends BaseControllerTest {

    @Test
    @DisplayName("ErrorCode 문서화")
    public void errorCodeDocumentation() throws Exception {
        // given + when
        ResultActions result = this.mockMvc.perform(get("/errors")
                .session(memberSession)
                .accept(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(document("에러 코드",
                codeResponseFields("code-response", beneathPath("error_codes"),
                        attributes(key("title").value("에러 코드")),
                        enumConvertFieldDescriptor(ErrorCode.values())
                )
        ));
    }

    private FieldDescriptor[] enumConvertFieldDescriptor(ErrorCode[] errorCodes) {
        return Arrays.stream(errorCodes)
                .map(enumType -> fieldWithPath(enumType.getCode()).description(enumType.getDescription()))
                .toArray(FieldDescriptor[]::new);
    }

    public static CodeResponseFieldsSnippet codeResponseFields(String type,
                                                               PayloadSubsectionExtractor<?> subsectionExtractor,
                                                               Map<String, Object> attributes, FieldDescriptor... descriptors) {
        return new CodeResponseFieldsSnippet(type, subsectionExtractor, Arrays.asList(descriptors), attributes
                , true);
    }

}