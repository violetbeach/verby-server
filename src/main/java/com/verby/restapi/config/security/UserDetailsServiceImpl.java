package com.verby.restapi.config.security;

import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("loginId not found (id: %s)", loginId)));

        return new SecurityUser(
                user.getId(),
                user.getLoginId(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(
                        user.getRoles().stream().map(r -> String.format("ROLE_%s", r.getName().toString()))
                                .toArray(String[]::new)
                )
        );
    }

}
