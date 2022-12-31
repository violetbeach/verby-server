package com.verby.core.user.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.EntityDuplicateException;

public class PhoneNumberDuplicateException extends EntityDuplicateException {

    public PhoneNumberDuplicateException(String phoneNumber) {
        super(ErrorCode.DUPLICATE_PHONE_NUMBER, String.format("phoneNumber (%s) is duplicated.", phoneNumber));
    }
}
