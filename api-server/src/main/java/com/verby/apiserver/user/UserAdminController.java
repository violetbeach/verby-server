package com.verby.apiserver.user;

import com.verby.core.user.command.application.CreateAdminRequest;
import com.verby.core.user.command.application.CreatedUserInfo;
import com.verby.core.user.command.application.UserAdminService;
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
    private ResponseEntity<CreatedUserInfo> create(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        CreatedUserInfo userInfo = userAdminService.createAdmin(createAdminRequest);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
