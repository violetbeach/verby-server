package com.verby.restapi.user.query.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchParam {

    private String loginId;
    private String phone;

}
