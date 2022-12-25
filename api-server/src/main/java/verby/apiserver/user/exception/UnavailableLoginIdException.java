package verby.apiserver.user.exception;

import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.BusinessException;

public class UnavailableLoginIdException extends BusinessException {

    public UnavailableLoginIdException(String loginId) {
        super(ErrorCode.UNAVAILABLE_LOGIN_ID, String.format("login id (%s) is unavailable.", loginId));
    }

}
