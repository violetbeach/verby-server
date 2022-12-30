package com.verby.apiserver.user.query.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchParam {

    private String loginId;
    private String phone;

}
