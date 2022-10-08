package com.verby.restapi.user.command.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResetPasswordRequest {

    @JsonIgnore
    private String token;

    @NotNull @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$", message = "영문과 숫자를 조합해서 사용해야합니다")
    private String newPassword;

    public void setToken(String token) {
        this.token = token;
    }
}
