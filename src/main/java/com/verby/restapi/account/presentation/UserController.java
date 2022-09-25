package com.verby.restapi.account.presentation;

import com.verby.restapi.account.command.application.ResetNicknameRequest;
import com.verby.restapi.account.command.application.UserService;
import com.verby.restapi.account.query.dao.UserDataDao;
import com.verby.restapi.account.query.dto.UserData;
import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.config.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserDataDao userDataDao;

    @PutMapping("/me/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetNickname(@AuthenticationPrincipal SecurityUser user, @RequestBody @Valid ResetNicknameRequest request) {
        request.setUserId(user.getUserId());
        userService.resetNickname(request);
    }

    @GetMapping("/me")
    private ResponseEntity<UserData> me(@AuthenticationPrincipal SecurityUser user) {
        UserData userData = userDataDao.findById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "User not found."));

        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

}
