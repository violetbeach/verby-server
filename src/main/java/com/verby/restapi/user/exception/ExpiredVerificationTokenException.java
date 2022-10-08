package com.verby.restapi.user.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.BusinessException;

public class ExpiredVerificationTokenException extends BusinessException {

    public ExpiredVerificationTokenException(String token) {
        super(ErrorCode.EXPIRED_VERIFICATION_TOKEN, String.format("Verification Token (%s) is expired.", token));
    }

}
