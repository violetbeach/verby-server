package com.verby.core.user.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class InvalidCertificationNumberException extends BusinessException {

    public InvalidCertificationNumberException(int certificationNumber) {
        super(ErrorCode.INVALID_CERTIFICATION_NUMBER, String.format("Certification number (%s) is invalid.", certificationNumber));
    }

}
