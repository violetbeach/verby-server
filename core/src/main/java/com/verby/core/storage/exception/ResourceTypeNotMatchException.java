package com.verby.core.storage.exception;


import com.verby.apiserver.common.error.ErrorCode;
import com.verby.core.common.error.exception.BusinessException;
import com.verby.core.storage.dto.Domain;
import com.verby.core.storage.dto.Resource;

public class ResourceTypeNotMatchException extends BusinessException {

    public ResourceTypeNotMatchException(Domain domain, Resource resource) {
        super(ErrorCode.RESOURCE_TYPE_MISMATCH, String.format("%s is not support %s.", domain, resource));
    }
}
