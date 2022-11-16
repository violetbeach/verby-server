package com.verby.restapi.user.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.BusinessException;
import com.verby.restapi.user.command.CertificationType;

public class CertificationTypeNotMatchException extends BusinessException {

    public CertificationTypeNotMatchException(CertificationType expected, CertificationType type) {
        super(ErrorCode.CERTIFICATION_TYPE_NOT_MATCH, String.format("Certification type (%s) is not match (%s).", expected.name(), type.name()));
    }

}
