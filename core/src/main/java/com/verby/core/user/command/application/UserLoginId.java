package com.verby.core.user.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserLoginId {

    private final long id;
    private final String loginId;
    private final LocalDateTime createdAt;

}
