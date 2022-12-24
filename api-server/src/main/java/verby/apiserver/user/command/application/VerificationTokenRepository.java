package verby.apiserver.user.command.application;

import org.springframework.data.repository.Repository;
import verby.apiserver.user.command.domain.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends Repository<VerificationToken, Long> {

    VerificationToken save(VerificationToken verificationToken);
    Optional<VerificationToken> findByKey(String key);
    void delete(VerificationToken verificationToken);

}
