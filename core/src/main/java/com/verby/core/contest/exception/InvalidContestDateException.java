package com.verby.core.contest.exception;

import com.verby.core.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;

public class InvalidContestDateException extends BusinessException {

    public InvalidContestDateException() {
        super(ErrorCode.CONTEST_INVALID_DATE, "Start time is later thane end time");
    }
}
