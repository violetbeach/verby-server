package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountInfo signUp(SignUpRequest request) {
        if(accountRepository.existsByLoginId(request.getLoginId())) {
            throw new LoginIdDuplicateException(request.getLoginId());
        }

        AccountRole role = roleRepository.findByName(Role.MEMBER);

        Account newAccount = new Account(request.getLoginId(),
                passwordEncoder.encode(request.getPassword()),
                request.getName(),
                request.getPhone(),
                AccountStatus.ACTIVE,
                new HashSet<>(List.of(role)),
                request.getAllowToMarketingNotification()
        );

        accountRepository.save(newAccount);

        return AccountInfo.of(newAccount);
    }

}
