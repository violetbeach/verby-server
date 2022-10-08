package com.verby.restapi.contest.exception;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.BusinessException;

public class InvalidContestDateException extends BusinessException {

    public InvalidContestDateException() {
        super(ErrorCode.CONTEST_INVALID_DATE, "Start time is later thane end time");
    }
}
