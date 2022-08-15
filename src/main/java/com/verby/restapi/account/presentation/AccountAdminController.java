package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.AccountAdminService;
import com.verby.restapi.account.command.application.AccountInfo;
import com.verby.restapi.account.command.application.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/accounts")
@RequiredArgsConstructor
public class AccountAdminController {

    private final AccountAdminService accountAdminService;

    @PostMapping
    private ResponseEntity<AccountInfo> create(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        AccountInfo accountData = accountAdminService.createAdmin(createAdminRequest);
        return new ResponseEntity<>(accountData, HttpStatus.CREATED);
    }

}
