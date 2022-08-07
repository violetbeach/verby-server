package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.AccountInfo;
import com.verby.restapi.account.command.application.AccountService;
import com.verby.restapi.account.command.application.SignUpRequest;
import com.verby.restapi.account.query.application.AccountAuthService;
import com.verby.restapi.account.query.dto.AccountLoginId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountAuthService accountAuthService;

    @PostMapping
    private ResponseEntity<AccountInfo> signup(@RequestBody @Valid SignUpRequest signUpRequest) {
        AccountInfo accountData = accountService.signUp(signUpRequest);
        return new ResponseEntity<>(accountData, HttpStatus.CREATED);
    }

    @GetMapping("/login-id")
    private ResponseEntity<AccountLoginId> findByPhoneAuth(@RequestParam String phone) {
        AccountLoginId accountLoginId = accountAuthService.findLoginIdByPhoneAuth(phone);
        return new ResponseEntity<>(accountLoginId, HttpStatus.OK);
    }

}
