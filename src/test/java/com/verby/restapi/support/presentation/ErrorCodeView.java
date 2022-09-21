package com.verby.restapi.common.error.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorCodeView {
    private Map<String, String> errorCodes;
}
