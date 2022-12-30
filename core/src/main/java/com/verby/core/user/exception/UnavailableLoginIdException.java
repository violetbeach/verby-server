package com.verby.core.user.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.BusinessException;

public class UnavailableLoginIdException extends BusinessException {

    public UnavailableLoginIdException(String loginId) {
        super(ErrorCode.UNAVAILABLE_LOGIN_ID, String.format("login id (%s) is unavailable.", loginId));
    }

}
