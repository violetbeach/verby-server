package com.verby.core.cover.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class CoverLikeExistsAlreadyException extends BusinessException {

    public CoverLikeExistsAlreadyException(ErrorCode errorCode) {
        super(errorCode, "Cover Like is exists already.");
    }

}
