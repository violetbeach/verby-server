package com.verby.core.user.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.EntityDuplicateException;

public class LoginIdDuplicateException extends EntityDuplicateException {

    public LoginIdDuplicateException(String loginId) {
        super(ErrorCode.DUPLICATE_LOGIN_ID, String.format("loginId (%s) is duplicated.", loginId));
    }
}
