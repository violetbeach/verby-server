package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.ResetNicknameRequest;
import com.verby.restapi.user.command.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetNickname(@PathVariable long id, @RequestBody @Valid ResetNicknameRequest resetNicknameRequest) {
        resetNicknameRequest.setUserId(id);
        userService.resetNickname(resetNicknameRequest);
    }

}
