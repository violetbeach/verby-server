package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.UserLoginId;
import com.verby.restapi.user.command.application.ResetPasswordRequest;
import com.verby.restapi.user.command.application.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @GetMapping("/login-id")
    private ResponseEntity<UserLoginId> findByPhoneAuth(@RequestParam String token) {
        UserLoginId loginId = userAuthService.findLoginId(token);
        return new ResponseEntity<>(loginId, HttpStatus.OK);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        userAuthService.resetPassword(request);
    }

}
