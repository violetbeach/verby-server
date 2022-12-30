package com.verby.core.user.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.EntityDuplicateException;

public class TokenNotFoundException extends EntityDuplicateException {

    public TokenNotFoundException(String token) {
        super(ErrorCode.TOKEN_NOT_EXISTS, String.format("token (%s) is not exists.", token));
    }
}
