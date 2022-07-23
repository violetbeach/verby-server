package com.verby.restapi.common.exception;

public class NotUniqueException extends RuntimeException {

    public NotUniqueException(String domain, String property, String value) {
        super(String.format("%s's %s(%s) is not unique.", domain, property, value));
    }
}
