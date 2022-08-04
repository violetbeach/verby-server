package com.verby.restapi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        return new UsernamePasswordAuthenticationToken(userDetails
                , userDetails.getPassword()
                , userDetails.getAuthorities());
    }

}
