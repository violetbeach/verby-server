package verby.apiserver.config.security.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;
import verby.apiserver.config.security.AuthenticationRequest;
import verby.apiserver.support.presentation.BaseControllerTest;
import verby.apiserver.user.command.domain.Role;
import verby.apiserver.user.command.domain.RoleRepository;
import verby.apiserver.user.command.domain.UserRepository;
import verby.apiserver.user.command.domain.UserRole;

import java.util.Set;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentRequest;
import static verby.apiserver.support.documentation.ApiDocumentUtils.getDocumentResponse;
import static verby.apiserver.support.fixture.domain.UserFixture.NORMAL_USER;

class AuthenticationFilterTest extends BaseControllerTest {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("유저 정보를 이용해 세션을 생성할 수 있다.")
    void attemptAuthentication() throws Exception {
        // given
        String loginId = "test1234";
        String password = "test5678";
        generateUser(loginId, password);
        AuthenticationRequest request = new AuthenticationRequest(loginId, password);

        // when
        ResultActions result = mockMvc.perform(post("/users/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isNoContent());

        // docs
        result.andDo(document("세션 등록(로그인)",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                        fieldWithPath("login_id").type(JsonFieldType.STRING).description("계정 로그인 ID"),
                        fieldWithPath("password").type(JsonFieldType.STRING).description("계정 비밀번호")
                )
        ));
    }

    void generateUser(String loginId, String password) {
        UserRole role = roleRepository.findByName(Role.MEMBER);
        userRepository.save(NORMAL_USER.getUser(loginId, passwordEncoder.encode(password), Set.of(role)));
    }

}