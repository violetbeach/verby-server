package com.verby.apiserver.user.presentation;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.fixture.domain.UserFixture;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.core.user.command.application.SignUpRequest;
import com.verby.core.user.command.domain.RoleRepository;
import com.verby.core.user.command.domain.User;
import com.verby.core.user.command.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    @DisplayName("SignupRequest에 loginId가 비어있으면 400(INVALID_INPUT_VALUE)을 반환한다.")
    void signup_fail_emptyLoginId() throws Exception {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("")
                .password("password12@")
                .name("TestName")
                .birthday(LocalDate.of(1994, 2, 10))
                .phone("01012345678")
                .allowToMarketingNotification(true)
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("code").value(ErrorCode.INVALID_INPUT_VALUE.getCode()));
    }

    @Test
    @DisplayName("UserSearchParam으로 유저 중복 여부를 확인할 수 있다.")
    void existsUser() throws Exception {
        // given
        String loginId = "VioletBeach1";
        String phone = "01030492093";
        generateUser(loginId, phone);

        // when
        ResultActions result = mockMvc.perform(head("/users")
                .param("loginId", loginId)
                .param("phone", phone));

        // then
        result.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("유저 존재 여부 확인",
                        ApiDocumentUtils.getDocumentRequest(),
                        ApiDocumentUtils.getDocumentResponse(),
                        requestParameters(
                                parameterWithName("loginId").description("계정 로그인 아이디"),
                                parameterWithName("phone").description("휴대폰 번호")
                        )));
    }

    @Test
    @DisplayName("세션의 회원 정보를 조회할 수 있다.")
    @WithUserDetails("member")
    void me() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/users/me"));

        // then
        result.andExpect(status().isOk());

        // docs
        result.andDo(MockMvcRestDocumentation.document("유저 정보 조회",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("로그인 ID"),
                        fieldWithPath("bio").type(JsonFieldType.STRING).description("소개 글").optional(),
                        fieldWithPath("profile_image").type(JsonFieldType.STRING).description("프로필 이미지").optional()
                ))
        );
    }

    void generateUser(String loginId) {
        userRepository.save(UserFixture.NORMAL_USER.getUser(loginId));
    }

    User generateUser(String loginId, String phone) {
        return userRepository.save(UserFixture.NORMAL_USER.getUser(loginId, phone));
    }

}