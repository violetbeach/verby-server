package com.verby.restapi.user.command.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "unavailable_id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UnavailableID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String loginId;

    public UnavailableID(String loginId) {
        this.loginId = loginId;
    }
}
