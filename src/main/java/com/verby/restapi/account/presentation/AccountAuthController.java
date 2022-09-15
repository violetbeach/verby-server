package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.AccountAuthService;
import com.verby.restapi.account.command.application.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountAuthController {

    private final AccountAuthService accountAuthService;

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        accountAuthService.resetPassword(request);
    }

}
