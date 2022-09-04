package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.ResetPasswordRequest;
import com.verby.restapi.account.command.application.VerificationRepository;
import com.verby.restapi.account.command.domain.*;
import com.verby.restapi.common.presentation.BaseControllerTest;
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
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountAuthControllerTest extends BaseControllerTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    VerificationRepository verificationRepository;

    @Test
    @DisplayName("비밀번호를 재설정할 수 있다.")
    void resetPassword() throws Exception {
        // given
        Account account = generateAccount();
        VerificationToken verificationToken = generateVerificationToken(VerificationType.SET_PASSWORD, account);

        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("new_password1");

        // when
        ResultActions result = mockMvc.perform(put("/accounts/password")
                .session(memberSession)
                .param("token", verificationToken.getKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resetPasswordRequest)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("계정 비밀번호 재설정",
                getDocumentRequest(),
                getDocumentResponse(),
                requestParameters(
                        parameterWithName("token").description("인증 토큰 키")
                ),
                requestFields(
                        fieldWithPath("new_password").type(JsonFieldType.STRING).description("재설정할 비밀번호")
                )));
    }

    VerificationToken generateVerificationToken(VerificationType type, Account account) {
        VerificationToken verificationToken = new VerificationToken(type, account);
        return verificationRepository.save(verificationToken);
    };

    Account generateAccount() {
        Account account = new Account(
                "violetbeach13",
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