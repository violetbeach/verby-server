package verby.apiserver.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import verby.apiserver.user.command.application.*;
import verby.apiserver.user.command.domain.VerificationToken;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/send-certification-sms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void sendCertificationSMS(@RequestBody SendCertificationSMSRequest request) {
        userAuthService.sendCertificationSMS(request);
    }

    @PostMapping("/verification-tokens")
    private ResponseEntity<VerificationToken> createToken(@RequestBody SMSCertificationRequest request) {
        VerificationToken token = userAuthService.createToken(request);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping("/login-id")
    private ResponseEntity<UserLoginId> findLoginId(@RequestParam(value = "verificationToken") String token) {
        UserLoginId loginId = userAuthService.findLoginId(token);
        return new ResponseEntity<>(loginId, HttpStatus.OK);
    }

    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPassword(@RequestParam(value = "verificationToken") String token, @RequestBody @Valid ResetPasswordRequest request) {
        request.setToken(token);
        userAuthService.resetPassword(request);
    }

    @PostMapping("/reset-password/send-certification-sms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void resetPasswordCertificationSMS(@RequestBody SendResetPasswordSMSRequest request) {
        userAuthService.resetPasswordCertificationSMS(request);
    }

    @PostMapping
    private ResponseEntity<CreatedUserInfo> signup(@RequestBody @Valid SignUpRequest request) {
        CreatedUserInfo userInfo = userAuthService.signUp(request);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }

}
