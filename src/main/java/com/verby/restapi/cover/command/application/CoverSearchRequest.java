package com.verby.restapi.cover.command.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CoverSearchRequest {
    private Long coverIdLt;
    private Long contestId;
    private int pageSize = 10;
}
