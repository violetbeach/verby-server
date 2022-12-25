package verby.apiserver.user.exception;

import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message) {
        super(ErrorCode.INVALID_VERIFICATION_TOKEN, message);
    }

}
