package com.verby.restapi.user.command.application;

import com.verby.restapi.common.event.Events;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@RedisHash(value = "Certification", timeToLive = 300)
public class Certification {

    @Id
    String id;
    @Indexed
    String phone;
    int certificationNumber;

    public Certification(String phone, int certificationNumber) {
        this.phone = phone;
        this.certificationNumber = certificationNumber;
        Events.raise(new CertificationCreatedEvent(phone, certificationNumber, LocalDateTime.now()));
    }
}
