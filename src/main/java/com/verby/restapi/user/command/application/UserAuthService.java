package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.User;
import com.verby.restapi.user.command.domain.VerificationToken;
import com.verby.restapi.user.command.domain.VerificationType;
import com.verby.restapi.user.exception.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final VerificationTokenRepository verificationTokenRepository;

    private final PasswordEncoder passwordEncoder;

    public void resetPassword(ResetPasswordRequest request) {
        VerificationToken verificationToken = verificationTokenRepository.findByKeyAndType(request.getToken(), VerificationType.SET_PASSWORD)
                .orElseThrow(() -> new TokenNotFoundException(request.getToken()));
        User user = verificationToken.getUser();

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.resetPassword(encodedPassword);
    }

}
