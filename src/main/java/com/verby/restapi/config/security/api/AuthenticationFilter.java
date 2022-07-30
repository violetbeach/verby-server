package com.verby.restapi.config.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher("/accounts/login", "POST"), authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        authRequest.setDetails(request);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Data
    private static class LoginDto {
        String username;
        String password;
    }

}