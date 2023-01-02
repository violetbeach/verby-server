package com.verby.core.user.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_VERIFICATION_TOKEN, message);
    }

}
