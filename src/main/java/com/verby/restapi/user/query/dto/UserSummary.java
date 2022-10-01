package com.verby.restapi.user.query.dto;

import lombok.Getter;

@Getter
public class UserSummary {

    private Long id;

    private String loginId;

    private String bio;

    private String profileImage;

    public UserSummary(Long id, String loginId, String bio, String profileImage) {
        this.id = id;
        this.loginId = loginId;
        this.bio = bio;
        this.profileImage = profileImage;
    }
}
