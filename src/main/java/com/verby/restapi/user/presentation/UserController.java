package com.verby.restapi.user.presentation;

import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.user.command.application.UserInfo;
import com.verby.restapi.user.command.application.UserService;
import com.verby.restapi.user.command.application.SignUpRequest;
import com.verby.restapi.user.query.application.UserQueryService;
import com.verby.restapi.user.query.dao.UserDataDao;
import com.verby.restapi.user.query.dto.UserLoginId;
import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.user.query.dto.UserData;
import com.verby.restapi.user.query.dto.UserSummary;
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
    private final UserQueryService userQueryService;
    private final UserDataDao userDataDao;

    @PostMapping
    private ResponseEntity<UserInfo> signup(@RequestBody @Valid SignUpRequest request) {
        UserInfo userData = userService.signUp(request);
        return new ResponseEntity<>(userData, HttpStatus.CREATED);
    }

    @GetMapping("/login-id")
    private ResponseEntity<UserLoginId> findByPhoneAuth(@RequestParam String phone) {
        UserLoginId userLoginId = userQueryService.findLoginIdByPhoneAuth(phone);
        return new ResponseEntity<>(userLoginId, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.HEAD)
    private ResponseEntity<UserData> existsLoginId(@RequestParam(value = "login-id") String loginId) {
        UserData userData = userDataDao.findByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "Not found."));
        return new ResponseEntity<>(userData, HttpStatus.OK);
    }

    @GetMapping("/me")
    private ResponseEntity<UserSummary> me(@AuthenticationPrincipal SecurityUser user) {
        UserSummary userSummary = userDataDao.findSummaryById(user.getUserId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "User not found."));

        return new ResponseEntity<>(userSummary, HttpStatus.OK);
    }


}
