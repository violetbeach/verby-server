package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public AccountData signUp(SignUpRequest signUpRequest) {
        AccountRole role = roleRepository.findByName(Role.MEMBER);

        Account newAccount = new Account(signUpRequest.getLoginId(),
                signUpRequest.getPassword(),
                signUpRequest.getName(),
                signUpRequest.getPhone(),
                AccountStatus.ACTIVE,
                new HashSet<>(List.of(role)),
                signUpRequest.getAllowToMarketingNotification()
        );

        accountRepository.save(newAccount);

        return AccountData.of(newAccount);
    }

}
