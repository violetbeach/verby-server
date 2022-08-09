package com.verby.restapi.account.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role name;

}
