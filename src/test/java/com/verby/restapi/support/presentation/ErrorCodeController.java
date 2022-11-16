package com.verby.restapi.support.presentation;

import com.verby.restapi.common.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/errors")
public class ErrorCodeController {

    @GetMapping
    public ResponseEntity<ErrorCodeView> getErrorCodes() {

        Map<String, String> errorCodes = Arrays.stream(ErrorCode.values())
                .collect(Collectors.toMap(ErrorCode::getCode, ErrorCode::getDescription));

        return new ResponseEntity<>(new ErrorCodeView(errorCodes), HttpStatus.OK);
    }

}
