package com.verby.restapi.user.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityDuplicateException;

public class TokenNotFoundException extends EntityDuplicateException {

    public TokenNotFoundException(String token) {
        super(ErrorCode.TOKEN_NOT_EXISTS, String.format("token (%s) is not exists.", token));
    }
}
