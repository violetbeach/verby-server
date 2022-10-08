package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String loginId;

    static UserInfo from(User user) {
        return new UserInfo(user.getId(), user.getLoginId());
    }
}
