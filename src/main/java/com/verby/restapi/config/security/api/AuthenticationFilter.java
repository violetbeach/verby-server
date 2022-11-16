package com.verby.restapi.config.security.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verby.restapi.config.security.AuthenticationRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                AuthenticationSuccessHandler authenticationSuccessHandler,
                                AuthenticationFailureHandler authenticationFailureHandler,
                                ObjectMapper objectMapper) {
        super(new AntPathRequestMatcher("/users/sessions", "POST"), authenticationManager);
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthenticationRequest authenticationRequest = objectMapper.readValue(request.getInputStream(), AuthenticationRequest.class);
        String username = authenticationRequest.getLoginId();
        String password = authenticationRequest.getPassword();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

}