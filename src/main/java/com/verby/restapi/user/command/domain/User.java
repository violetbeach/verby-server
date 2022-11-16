package com.verby.restapi.user.command.domain;

import com.verby.restapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String phone;

    private String bio;

    private String profileImage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private Set<UserRole> roles;

    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    private boolean allowToMarketingNotification;

    public User(String loginId, String password, String name, String phone, Gender gender, Set<UserRole> roles, boolean allowToMarketingNotification) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.roles = roles;
        this.status = UserStatus.ACTIVE;
        this.allowToMarketingNotification = allowToMarketingNotification;
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
