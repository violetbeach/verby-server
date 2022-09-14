package com.verby.restapi.user.presentation;

import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.AccountRepository;
import com.verby.restapi.account.command.domain.AccountStatus;
import com.verby.restapi.common.presentation.BaseControllerTest;
import com.verby.restapi.user.command.application.ResetNicknameRequest;
import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentRequest;
import static com.verby.restapi.common.presentation.ApiDocumentUtils.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("ResetNicknameRequest로 닉네임을 재설정할 수 있다.")
    void resetNickname() throws Exception {
        // given
        User user = generateUser();
        ResetNicknameRequest resetNicknameRequest = new ResetNicknameRequest(user.getId(), "updatedNickname");

        // when
        ResultActions result = mockMvc.perform(put("/users/{userId}/nickname", user.getId())
                .session(memberSession)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resetNicknameRequest)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("유저 닉네임 재설정",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                        parameterWithName("userId").description("유저 일련번호")
                ),
                requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("병경할 닉네임")
                ))
        );
    }

    User generateUser() {
        Account account = generateAccount("test");

        User user = new User(account.getId(), "nickname", "bio", "profile");
        return userRepository.save(user);
    }

    private Account generateAccount(String loginId) {
        Account account = new Account(
                loginId,
                "test1234",
                "testName",
                "01012345678",
                AccountStatus.ACTIVE,
                null,
                false
        );
        return accountRepository.save(account);
    }
}

