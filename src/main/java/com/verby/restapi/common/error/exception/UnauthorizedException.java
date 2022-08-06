package com.verby.restapi.common.error.exception;

import com.verby.restapi.common.error.ErrorCode;

public class UnAuthorizedException extends BusinessException {
    public UnAuthorizedException(String message) {
        super(ErrorCode.UNAUTHORIZED, message);
    }
}
