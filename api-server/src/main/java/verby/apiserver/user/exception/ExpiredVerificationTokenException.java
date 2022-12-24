package verby.apiserver.user.exception;

import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.BusinessException;

public class ExpiredVerificationTokenException extends BusinessException {

    public ExpiredVerificationTokenException(String token) {
        super(ErrorCode.EXPIRED_VERIFICATION_TOKEN, String.format("Verification Token (%s) is expired.", token));
    }

}
