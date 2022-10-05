package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/send-certification-sms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void sendFindIdSMS(@RequestBody SendCertificationSMSRequest request) {
        Certification certification = new Certification(request.getPhone(), generateCertificationNumber());
        certificationRepository.save(certification);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        userAuthService.resetPassword(request);
    }

}
