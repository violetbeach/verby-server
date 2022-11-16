package com.verby.restapi.common.error.exception;

import com.verby.restapi.common.error.ErrorCode;

public class StaticStorageExcpetion extends BusinessException {

    public StaticStorageExcpetion(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
