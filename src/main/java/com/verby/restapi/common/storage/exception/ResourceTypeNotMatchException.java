package com.verby.restapi.common.storage.exception;


import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.BusinessException;
import com.verby.restapi.common.storage.dto.Domain;
import com.verby.restapi.common.storage.dto.Resource;

public class ResourceTypeNotMatchException extends BusinessException {

    public ResourceTypeNotMatchException(Domain domain, Resource resource) {
        super(ErrorCode.RESOURCE_TYPE_MISMATCH, String.format("%s is not support %s.", domain, resource));
    }
}
