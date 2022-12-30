package com.verby.core.common.error.exception;

import com.verby.apiserver.common.error.ErrorCode;

public class StaticStorageExcpetion extends BusinessException {

    public StaticStorageExcpetion(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
