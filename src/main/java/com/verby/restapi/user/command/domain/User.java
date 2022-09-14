package com.verby.restapi.user.command.domain;

import com.verby.restapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private long accountId;

    private String nickname;

    private String bio;

    private String profileImage;

    public User(long accountId, String nickname, String bio, String profileImage) {
        this.accountId = accountId;
        this.nickname = nickname;
        this.bio = bio;
        this.profileImage = profileImage;
    }

    public void rename(String nickname) {
        this.nickname = nickname;
    }
}
