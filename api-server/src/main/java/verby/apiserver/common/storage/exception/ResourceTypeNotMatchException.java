package verby.apiserver.common.storage.exception;


import verby.apiserver.common.error.ErrorCode;
import verby.apiserver.common.error.exception.BusinessException;
import verby.apiserver.common.storage.dto.Domain;
import verby.apiserver.common.storage.dto.Resource;

public class ResourceTypeNotMatchException extends BusinessException {

    public ResourceTypeNotMatchException(Domain domain, Resource resource) {
        super(ErrorCode.RESOURCE_TYPE_MISMATCH, String.format("%s is not support %s.", domain, resource));
    }
}
