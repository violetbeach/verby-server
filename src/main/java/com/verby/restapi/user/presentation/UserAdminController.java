package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.UserAdminService;
import com.verby.restapi.user.command.application.UserInfo;
import com.verby.restapi.user.command.application.CreateAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserAdminController {

    private final UserAdminService userAdminService;

    @PostMapping
    private ResponseEntity<UserInfo> create(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        UserInfo userInfo = userAdminService.createAdmin(createAdminRequest);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
