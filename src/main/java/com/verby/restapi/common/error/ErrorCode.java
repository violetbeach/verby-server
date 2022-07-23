package com.verby.restapi.common.error;

public enum ErrorCode {

    DUPLICATE_LOGIN_ID(400, "ACCOUNT-001");

    private final int status;
    private final String code;

    ErrorCode(int status, String code) {
        this.status = status;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
