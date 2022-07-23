package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.SignUpRequest;
import com.verby.restapi.common.presentation.BasicControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends BasicControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        jdbcTemplate.update("truncate table account");
        jdbcTemplate.update("truncate table account_role");
    }

    @Test
    @DisplayName("유저를 생성할 수 있다.")
    void signup() throws Exception {
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .loginId("TestId")
                .password("TestPassword")
                .name("TestName")
                .birthday(LocalDateTime.now())
                .phone("01012345678")
                .allowToMarketingNotification(true)
                .build();

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signUpRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("login_id").value(signUpRequest.getLoginId()))
               ;
    }


}