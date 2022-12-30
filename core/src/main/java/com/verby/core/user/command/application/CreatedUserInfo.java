package com.verby.core.user.command.application;

import com.verby.core.user.command.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreatedUserInfo {
    private Long id;
    private String loginId;
    private LocalDateTime createdAt;

    static CreatedUserInfo from(User user) {
        return new CreatedUserInfo(user.getId(), user.getLoginId(), user.getCreatedAt());
    }
}
