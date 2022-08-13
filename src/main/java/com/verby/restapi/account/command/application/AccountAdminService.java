package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class AccountAdminService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountInfo createAdmin(CreateAdminRequest createAdminRequest) {
        if(accountRepository.existsByLoginId(createAdminRequest.getLoginId())) {
            throw new LoginIdDuplicateException(createAdminRequest.getLoginId());
        }

        HashSet<AccountRole> roles = new HashSet<>();
        roles.add(roleRepository.findByName(Role.MEMBER));
        roles.add(roleRepository.findByName(Role.ADMIN));

        Account newAdminAccount = new Account(createAdminRequest.getLoginId(),
                passwordEncoder.encode(createAdminRequest.getPassword()),
                createAdminRequest.getName(),
                null,
                AccountStatus.ACTIVE,
                roles,
                false
        );

        accountRepository.save(newAdminAccount);

        return AccountInfo.of(newAdminAccount);
    }

}
