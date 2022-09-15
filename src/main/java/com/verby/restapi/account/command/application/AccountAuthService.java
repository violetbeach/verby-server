package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.Account;
import com.verby.restapi.account.command.domain.VerificationToken;
import com.verby.restapi.account.command.domain.VerificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountAuthService {

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public void resetPassword(ResetPasswordRequest request) {
        VerificationToken verificationToken = verificationTokenRepository.findByKeyAndType(request.getToken(), VerificationType.SET_PASSWORD)
                .orElseThrow(() -> new TokenNotFoundException(request.getToken()));
        Account account = verificationToken.getAccount();

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        account.resetPassword(encodedPassword);
    }

}
