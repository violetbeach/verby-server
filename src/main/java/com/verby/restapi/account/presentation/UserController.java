package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.ResetNicknameRequest;
import com.verby.restapi.account.command.application.UserService;
import com.verby.restapi.config.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/me/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetNickname(@AuthenticationPrincipal SecurityUser user, @RequestBody @Valid ResetNicknameRequest resetNicknameRequest) {
        resetNicknameRequest.setUserId(user.getUserId());
        userService.resetNickname(resetNicknameRequest);
    }

}
