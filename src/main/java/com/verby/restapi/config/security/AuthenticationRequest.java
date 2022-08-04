package com.verby.restapi.config.security;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    String username;
    String password;
}
