package com.verby.apiserver.user.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.BusinessException;

public class ExpiredVerificationTokenException extends BusinessException {

    public ExpiredVerificationTokenException(String token) {
        super(ErrorCode.EXPIRED_VERIFICATION_TOKEN, String.format("Verification Token (%s) is expired.", token));
    }

}
