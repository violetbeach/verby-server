package com.verby.core.user.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class TokenNotFoundException extends BusinessException {

    public TokenNotFoundException(String token) {
        super(ErrorCode.TOKEN_NOT_EXISTS, String.format("token (%s) is not exists.", token));
    }
}
