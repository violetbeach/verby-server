package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.AccountData;
import com.verby.restapi.account.command.application.AccountService;
import com.verby.restapi.account.command.application.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    private ResponseEntity<AccountData> signup(@RequestBody SignUpRequest signUpRequest) {
        AccountData accountData = accountService.signUp(signUpRequest);
        return new ResponseEntity<>(accountData, HttpStatus.CREATED);
    }

}
