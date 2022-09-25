package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.*;
import com.verby.restapi.account.exception.LoginIdDuplicateException;
import com.verby.restapi.account.exception.PhoneNumberDuplicateException;
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
        verifyUniqueLoginId(request.getLoginId());
        verifyUniquePhoneNumber(request.getPhone());

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

        return AccountInfo.from(newAccount);
    }

    private void verifyUniqueLoginId(String loginId) {
        if(accountRepository.existsByLoginId(loginId)) {
            throw new LoginIdDuplicateException(loginId);
        }
    }

    private void verifyUniquePhoneNumber(String phoneNumber) {
        if(accountRepository.existsByPhone(phoneNumber)) {
            throw new PhoneNumberDuplicateException(phoneNumber);
        }
    }

}
