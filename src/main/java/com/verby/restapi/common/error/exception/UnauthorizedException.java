package com.verby.restapi.common.error.exception;

import com.verby.restapi.common.error.ErrorCode;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException() {
        super(ErrorCode.UNAUTHORIZED, "Not accessible");
    }
}
