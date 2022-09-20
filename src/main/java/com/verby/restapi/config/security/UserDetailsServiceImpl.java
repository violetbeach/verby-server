package com.verby.restapi.config.security;

import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = accountRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("loginId not found"));

        return new SecurityUser(
                account.getLoginId(),
                account.getPassword(),
                account.getUser().getId(),
                AuthorityUtils.createAuthorityList(
                        account.getRoles().stream().map(r -> String.format("ROLE_%s", r.getName().toString()))
                                .toArray(String[]::new)
                )
        );
    }
}
