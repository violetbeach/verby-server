package com.verby.restapi.user.command.application;

import com.verby.restapi.user.command.domain.VerificationToken;
import com.verby.restapi.user.command.domain.VerificationType;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface VerificationTokenRepository extends Repository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);
    Optional<VerificationToken> findByKeyAndType(String key, VerificationType type);

}
