package com.verby.restapi.account.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ResetNicknameRequest {

    @JsonIgnore
    private Long userId;
    @NotBlank
    private String name;

    public ResetNicknameRequest(String name) {
        this.name = name;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
