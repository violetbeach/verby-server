package verby.apiserver.common.error.exception;

import verby.apiserver.common.error.ErrorCode;

public class EntityDuplicateException extends BusinessException {

    public EntityDuplicateException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
