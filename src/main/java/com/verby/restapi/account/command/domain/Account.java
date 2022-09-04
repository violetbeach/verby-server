package com.verby.restapi.account.command.domain;

import com.verby.restapi.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String password;

    private String name;

    private LocalDate birthday;

    @Column(unique = true)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role",
            joinColumns = { @JoinColumn(name = "account_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private Set<AccountRole> roles;

    private boolean allowToMarketingNotification;

    public Account(String loginId, String password, String name, String phone, AccountStatus status, Set<AccountRole> roles, boolean allowToMarketingNotification) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.roles = roles;
        this.allowToMarketingNotification = allowToMarketingNotification;
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }


}
