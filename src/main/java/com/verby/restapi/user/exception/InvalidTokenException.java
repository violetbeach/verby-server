package com.verby.restapi.user.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_VERIFICATION_TOKEN, message);
    }

}
