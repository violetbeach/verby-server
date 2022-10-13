package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.*;
import com.verby.restapi.user.command.domain.VerificationToken;
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

    @PostMapping("/verification-tokens")
    private ResponseEntity<VerificationToken> createToken(@RequestBody SMSCertificationRequest request) {
        VerificationToken token = userAuthService.createToken(request);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping("/login-id")
    private ResponseEntity<UserLoginId> findLoginId(@RequestParam(value = "verification_token") String token) {
        UserLoginId loginId = userAuthService.findLoginId(token);
        return new ResponseEntity<>(loginId, HttpStatus.OK);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam(value = "verification_token") String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        userAuthService.resetPassword(request);
    }

    @PostMapping
    private ResponseEntity<UserInfo> signup(@RequestBody @Valid SignUpRequest request) {
        UserInfo userInfo = userAuthService.signUp(request);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
