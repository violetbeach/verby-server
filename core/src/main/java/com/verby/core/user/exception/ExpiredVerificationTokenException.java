package com.verby.core.user.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class ExpiredVerificationTokenException extends BusinessException {

    public ExpiredVerificationTokenException(String token) {
        super(ErrorCode.EXPIRED_VERIFICATION_TOKEN, String.format("Verification Token (%s) is expired.", token));
    }

}
