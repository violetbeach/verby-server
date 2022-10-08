package com.verby.restapi.user.presentation;

import com.verby.restapi.common.error.ErrorCode;
import com.verby.restapi.common.error.exception.EntityNotFoundException;
import com.verby.restapi.config.security.SecurityUser;
import com.verby.restapi.user.query.dao.UserDataDao;
import com.verby.restapi.user.query.dto.UserData;
import com.verby.restapi.user.query.dto.UserSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDataDao userDataDao;

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
