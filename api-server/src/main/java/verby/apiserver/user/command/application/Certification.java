package verby.apiserver.user.command.application;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import verby.apiserver.common.event.Events;
import verby.apiserver.config.database.RedisHashKey;
import verby.apiserver.user.command.CertificationType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = RedisHashKey.CERTIFICATION, timeToLive = 300)
public class Certification {

    @Id
    protected String id;
    @Indexed
    protected final String phone;
    protected final int certificationNumber;
    @Enumerated(EnumType.STRING)
    protected CertificationType type;

    public Certification(String phone, int certificationNumber, CertificationType type) {
        this.phone = phone;
        this.certificationNumber = certificationNumber;
        this.type = type;
        Events.raise(new CertificationCreatedEvent(phone, certificationNumber, LocalDateTime.now()));
    }


}
