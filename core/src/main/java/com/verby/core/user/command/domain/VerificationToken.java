package com.verby.core.user.command.domain;

import com.verby.core.user.exception.ExpiredVerificationTokenException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "phone_verification_token")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    private String phone;

    private LocalDateTime expirationDate;

    @CreatedDate
    private LocalDateTime createdAt;

    public VerificationToken(String phone) {
        this.key = UUID.randomUUID().toString();
        this.phone = phone;
        setExpirationDate();
    }

    public void verifyExpirationDate() {
        if(expirationDate.isBefore(LocalDateTime.now())) {
            throw new ExpiredVerificationTokenException(key);
        }
    }

    private void setExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        this.expirationDate = now.plusDays(7);
    }
}
