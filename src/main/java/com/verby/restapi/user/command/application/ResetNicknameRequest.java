package com.verby.restapi.user.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResetNicknameRequest {

    @JsonIgnore
    private Long userId;
    @NotBlank
    private String name;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
