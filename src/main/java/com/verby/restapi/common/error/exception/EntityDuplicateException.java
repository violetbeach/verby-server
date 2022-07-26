package com.verby.restapi.common.error.exception;

import com.verby.restapi.common.error.ErrorCode;

public class EntityDuplicateException extends BusinessException {

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
