package com.verby.core.common.error.exception;

import com.verby.core.common.error.ErrorCode;

public class EntityDuplicateException extends BusinessException {

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
