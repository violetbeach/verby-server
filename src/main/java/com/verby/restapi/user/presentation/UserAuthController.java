package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/send-certification-sms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void sendCertificationSMS(@RequestBody SendCertificationSMSRequest request) {
        userAuthService.sendCertificationSMS(request);
    }

    @PostMapping("/find-id")
    private ResponseEntity<UserLoginId> findLoginId(@RequestBody SMSCertificationRequest request) {
        UserLoginId loginId = userAuthService.findLoginId(request);
        return new ResponseEntity<>(loginId, HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        userAuthService.resetPassword(request);
    }

    @PostMapping
    private ResponseEntity<UserInfo> signup(@RequestBody @Valid SignUpRequest request) {
        UserInfo userInfo = userAuthService.signUp(request);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
