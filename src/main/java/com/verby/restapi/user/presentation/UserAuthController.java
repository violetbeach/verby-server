package com.verby.restapi.user.presentation;

import com.verby.restapi.user.command.application.UserAuthService;
import com.verby.restapi.user.command.application.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        userAuthService.resetPassword(request);
    }

}
