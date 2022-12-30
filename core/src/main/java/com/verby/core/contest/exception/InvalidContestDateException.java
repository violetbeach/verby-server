package com.verby.core.contest.exception;

import com.verby.apiserver.common.error.ErrorCode;
import com.verby.apiserver.common.error.exception.BusinessException;

public class InvalidContestDateException extends BusinessException {

    public InvalidContestDateException() {
        super(ErrorCode.CONTEST_INVALID_DATE, "Start time is later thane end time");
    }
}
