package com.verby.restapi.user.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Table(name = "user_verification_token")
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private VerificationType type;

    private LocalDateTime expirationDate;

    @CreatedDate
    private LocalDateTime createdAt;

    public VerificationToken(VerificationType type, User user) {
        this.key = UUID.randomUUID().toString();
        this.type = type;
        this.user = user;
        setExpirationDate(type);
    }

    private void setExpirationDate(VerificationType type) {
        LocalDateTime now = LocalDateTime.now();
        if(type == VerificationType.SET_PASSWORD) {
            this.expirationDate = now.plusDays(7);
        }
    }
}