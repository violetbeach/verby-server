package verby.apiserver.common.error.exception;

import verby.apiserver.common.error.ErrorCode;

public class StaticStorageExcpetion extends BusinessException {

    public StaticStorageExcpetion(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
