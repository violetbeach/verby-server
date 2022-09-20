package com.verby.restapi.account.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityDuplicateException;

public class PhoneNumberDuplicateException extends EntityDuplicateException {

    public PhoneNumberDuplicateException(String phoneNumber) {
        super(ErrorCode.DUPLICATE_PHONE_NUMBER, String.format("phoneNumber (%s) is duplicated.", phoneNumber));
    }
}
