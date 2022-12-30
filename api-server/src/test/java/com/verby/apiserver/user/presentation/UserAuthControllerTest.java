package com.verby.apiserver.user.presentation;

import com.verby.apiserver.support.documentation.ApiDocumentUtils;
import com.verby.apiserver.support.fixture.domain.UserFixture;
import com.verby.apiserver.support.presentation.BaseControllerTest;
import com.verby.apiserver.user.command.CertificationType;
import com.verby.apiserver.user.command.application.*;
import com.verby.apiserver.user.command.domain.Gender;
import com.verby.apiserver.user.command.domain.User;
import com.verby.apiserver.user.command.domain.UserRepository;
import com.verby.apiserver.user.command.domain.VerificationToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserAuthControllerTest extends BaseControllerTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VerificationTokenRepository verificationRepository;
    @Autowired
    CertificationRepository certificationRepository;

    @Test
    @DisplayName("SendCertificationSMSRequest로 인증번호를 발송할 수 있다.")
    void sendCertificationSMS() throws Exception {
        // given
        SendCertificationSMSRequest request = new SendCertificationSMSRequest("01013529380");

        // when
        ResultActions result = mockMvc.perform(post("/users/send-certification-sms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(MockMvcRestDocumentation.document("인증 SMS 발송",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대 전화 번호")
                )
        ));
    }

    @Test
    @DisplayName("SMSCertificationRequest로 토큰을 생성할 수 있다.")
    void createToken() throws Exception {
        // given
        String phone = "01020492039";
        Certification certification = generateCertification(phone, CertificationType.NORMAL);
        SMSCertificationRequest request = new SMSCertificationRequest(phone, certification.getCertificationNumber());

        // when
        ResultActions result = mockMvc.perform(post("/users/verification-tokens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("phone").value(phone));

        // docs
        result.andDo(MockMvcRestDocumentation.document("토큰 생성",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대 전화 번호"),
                        fieldWithPath("certification_number").type(JsonFieldType.NUMBER).description("SMS 인증 번호")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("토큰 일련번호"),
                        fieldWithPath("key").type(JsonFieldType.STRING).description("토큰 키"),
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대폰 번호"),
                        fieldWithPath("expiration_date").type(JsonFieldType.STRING).description("만료 일자"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )
        ));
    }

    @Test
    @DisplayName("VerificationToken으로 로그인 ID를 조회할 수 있다.")
    void findLoginId() throws Exception {
        // given
        User user = generateUser();
        VerificationToken verificationToken = generateVerificationToken(user.getPhone());

        // when
        ResultActions result = mockMvc.perform(get("/users/login-id")
                .param("verificationToken", verificationToken.getKey()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("login_id").value(user.getLoginId()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("ID 찾기",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestParameters(
                        parameterWithName("verificationToken").description("인증 토큰")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )
        ));
    }

    @Test
    @DisplayName("loginId, phone으로 비밀번호 찾기를 위한 인증번호를 발송할 수 있다.")
    void resetPasswordCertificationSMS() throws Exception {
        // given
        User user = generateUser();
        SendResetPasswordSMSRequest request = new SendResetPasswordSMSRequest(user.getPhone(), user.getLoginId());

        // when
        ResultActions result = mockMvc.perform(post("/users/reset-password/send-certification-sms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(MockMvcRestDocumentation.document("비밀번호 찾기 SMS 발송",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대 전화 번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("로그인 ID")
                )
        ));
    }

    @Test
    @DisplayName("ResetPasswordRequest로 비밀번호를 재설정할 수 있다.")
    void resetPassword() throws Exception {
        // given
        User user = generateUser();
        VerificationToken verificationToken = generateVerificationToken(user.getPhone());

        ResetPasswordRequest request = new ResetPasswordRequest(verificationToken.getKey(), "new_password1");

        // when
        ResultActions result = mockMvc.perform(put("/users/password")
                .param("verificationToken", verificationToken.getKey())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(MockMvcRestDocumentation.document("계정 비밀번호 재설정",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestParameters(
                        parameterWithName("verificationToken").description("인증 토큰")
                ),
                requestFields(
                        fieldWithPath("new_password").type(JsonFieldType.STRING).description("재설정할 비밀번호")
                )));
    }

    @Test
    @DisplayName("유저를 생성할 수 있다.")
    void signup() throws Exception {
        // given
        String phone = "01013524302";
        VerificationToken verificationToken = generateVerificationToken(phone);
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("test123")
                .password("password1234@")
                .name("TestName")
                .birthday(LocalDate.of(1994, 2, 10))
                .phone(phone)
                .gender(Gender.MALE)
                .allowToMarketingNotification(true)
                .token(verificationToken.getKey())
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signUpRequest)));

        // then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("login_id").value(signUpRequest.getLoginId()));

        // docs
        result.andDo(MockMvcRestDocumentation.document("계정 등록",
                ApiDocumentUtils.getDocumentRequest(),
                ApiDocumentUtils.getDocumentResponse(),
                requestFields(
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("계정 비밀번호"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                        fieldWithPath("phone").type(JsonFieldType.STRING).description("전화번호"),
                        fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                        fieldWithPath("birthday").type(JsonFieldType.STRING).description("생년월일").optional(),
                        fieldWithPath("allow_to_marketing_notification").type(JsonFieldType.BOOLEAN).description("마케팅 정보 수신 동의 여부"),
                        fieldWithPath("token").type(JsonFieldType.STRING).description("인증 토큰")

                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 일련번호"),
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("created_at").type(JsonFieldType.STRING).description("생성 일자")
                )));
    }

    VerificationToken generateVerificationToken(String phone) {
        VerificationToken verificationToken = new VerificationToken(phone);
        return verificationRepository.save(verificationToken);
    };

    private Certification generateCertification(String phone, CertificationType type) {
        return certificationRepository.save(new Certification(phone, 7248, type));
    }

    User generateUser() {
        return userRepository.save(UserFixture.NORMAL_USER.getUser());
    }
}