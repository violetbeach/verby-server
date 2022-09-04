package com.verby.restapi.account.command.application;

import com.verby.restapi.account.command.domain.VerificationToken;
import com.verby.restapi.account.command.domain.VerificationType;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface VerificationRepository extends Repository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);
    Optional<VerificationToken> findByKeyAndType(String key, VerificationType type);

}
