package com.verby.restapi.user.command.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CertificationCreatedEvent {

    private final String phone;
    private final int certificationNumber;
    private final LocalDateTime createdAt;

}
