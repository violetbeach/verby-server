package com.verby.apiserver.user.command.application;

import com.verby.apiserver.user.command.domain.VerificationToken;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface VerificationTokenRepository extends Repository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);
    Optional<VerificationToken> findByKey(String key);
    void delete(VerificationToken verificationToken);

}
