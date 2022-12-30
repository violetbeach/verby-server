package com.verby.apiserver.user.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.EntityDuplicateException;

public class PhoneNumberDuplicateException extends EntityDuplicateException {

    public PhoneNumberDuplicateException(String phoneNumber) {
        super(ErrorCode.DUPLICATE_PHONE_NUMBER, String.format("phoneNumber (%s) is duplicated.", phoneNumber));
    }
}
