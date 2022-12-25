package verby.apiserver.user.exception;

import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.EntityDuplicateException;

public class TokenNotFoundException extends EntityDuplicateException {

    public TokenNotFoundException(String token) {
        super(ErrorCode.TOKEN_NOT_EXISTS, String.format("token (%s) is not exists.", token));
    }
}
